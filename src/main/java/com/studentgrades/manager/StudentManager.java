package com.studentgrades.manager;

import com.studentgrades.dao.StudentDAO;
import com.studentgrades.exception.StudentException;
import com.studentgrades.exception.ValidationException;
import com.studentgrades.model.Student;
import com.studentgrades.util.StringUtils;
import com.studentgrades.validator.StudentFields;
import com.studentgrades.validator.CommonValidator;
import com.studentgrades.validator.StudentValidator;

import java.sql.SQLException;
import java.util.List;

public class StudentManager {

    private final StudentDAO dao = new StudentDAO();

    public boolean create(String firstName, String lastName) {
        String formatFirstName = StringUtils.formatName(firstName);
        String formatLastName = StringUtils.formatName(lastName);

        StudentValidator.validateName(formatFirstName);
        StudentValidator.validateName(formatLastName);

        try {
            if (dao.existsByName(formatFirstName, formatLastName)) {
                throw new StudentException("Ya existe un estudiante registrado con ese nombre y apellido.");
            }
        } catch (SQLException e) {
            throw new StudentException("Error al verificar existencia del estudiante.", e);
        }

        Student newStudent = new Student(formatFirstName, formatLastName);

        try {
            return dao.create(newStudent);
        } catch (SQLException e) {
            throw new StudentException("No se pudo registrar el estudiante.", e);
        }
    }

    public List<Student> getAll() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new StudentException("No se pudieron cargar los estudiantes.", e);
        }
    }

    public Student getById(int id) {
        StudentValidator.validateId(id);

        try {
            return dao.getById(id);
        } catch (SQLException e) {
            throw new StudentException("No se encontró el estudiante con ese id.", e);
        }
    }

    public List<Student> getByNames(String columnLabel, String name) {
        if (CommonValidator.isEmpty(columnLabel)) {
            throw new ValidationException("El campo a editar no puede estar vacío.");
        }

        StudentFields field = StudentFields.fromString(columnLabel);

        String normalizedName = StringUtils.normalizeForComparison(name);
        StudentValidator.validateName(normalizedName);

        try {
            return dao.getByNames(field.getFieldName(), normalizedName);
        } catch (SQLException e) {
            throw new StudentException("No se encontraron estudiantes.", e);
        }
    }

    public Student getByFirstAndLastName(String firstName, String lastName) {
        String normalizedFirstName = StringUtils.normalizeForComparison(firstName);
        String normalizedLastName = StringUtils.normalizeForComparison(lastName);

        StudentValidator.validateName(normalizedFirstName);
        StudentValidator.validateName(normalizedLastName);

        try {
            return dao.getByFirstAndLastName(normalizedFirstName, normalizedLastName);
        } catch (SQLException e) {
            throw new StudentException("No se encontró al estudiante con ese nombre y apellido.", e);
        }
    }

    public boolean update(int id, StudentFields field, String newName) {
        StudentValidator.validateId(id);

        if (field == null) {
            throw new ValidationException("El campo a editar no puede ser nulo.");
        }

        String formatName = StringUtils.formatName(newName);
        StudentValidator.validateName(formatName);

        try {
            return dao.update(id, field.getFieldName(), formatName);
        } catch (SQLException e) {
            throw new StudentException("No se pudo editar el estudiante.", e);
        }
    }

    public boolean delete(int id) {
        StudentValidator.validateId(id);

        try {
            return dao.delete(id);
        } catch (SQLException e) {
            throw new StudentException("No se pudo eliminar el estudiante.", e);
        }
    }
}
