package com.studentgrades.manager;

import com.studentgrades.dao.SubjectDAO;
import com.studentgrades.exception.StudentException;
import com.studentgrades.exception.SubjectException;
import com.studentgrades.model.Subject;
import com.studentgrades.util.StringUtils;
import com.studentgrades.validator.SubjectValidator;

import java.sql.SQLException;
import java.util.List;

public class SubjectManager {

    private final SubjectDAO dao = new SubjectDAO();

    public boolean create(String name) {
        String formatName = StringUtils.formatName(name);
        SubjectValidator.validateName(formatName);

        try {
            if (dao.exists(formatName)) {
                throw new SubjectException("Ya existe una materia registrada con ese nombre.");
            }
        } catch (SQLException e) {
            throw new StudentException("Error al verificar existencia de la materia.", e);
        }

        Subject newSubject = new Subject(formatName);

        try {
            return dao.create(newSubject);
        } catch (SQLException e) {
            throw new SubjectException("No se pudo registrar la materia.", e);
        }
    }

    public List<Subject> getAll() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new SubjectException("No se pudieron cargar las materias.", e);
        }
    }

    public Subject getById(int id) {
        SubjectValidator.validateId(id);

        try {
            return dao.getById(id);
        } catch (SQLException e) {
            throw new SubjectException("No se encontró la materia con ese id.", e);
        }
    }

    public boolean update(int id, String name) {
        SubjectValidator.validateId(id);

        String formatName = StringUtils.formatName(name);
        SubjectValidator.validateName(formatName);

        try {
            return dao.update(id, formatName);
        } catch (SQLException e) {
            throw new SubjectException("No se pudo editar la materia.", e);
        }
    }

    public boolean delete(int id) {
        SubjectValidator.validateId(id);

        try {
            return dao.delete(id);
        } catch (SQLException e) {
            throw new SubjectException("No se pudo eliminar la materia.", e);
        }
    }
}
