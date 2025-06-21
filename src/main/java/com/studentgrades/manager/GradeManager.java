package com.studentgrades.manager;

import com.studentgrades.dao.GradeDAO;
import com.studentgrades.dao.StudentDAO;
import com.studentgrades.dao.SubjectDAO;
import com.studentgrades.exception.GradeException;
import com.studentgrades.model.Grade;
import com.studentgrades.model.GradeView;
import com.studentgrades.model.Student;
import com.studentgrades.model.Subject;
import com.studentgrades.util.StringUtils;
import com.studentgrades.validator.GradeValidator;
import com.studentgrades.validator.StudentValidator;
import com.studentgrades.validator.SubjectValidator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class GradeManager {

    private final GradeDAO gradeDAO = new GradeDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();

    public boolean create(String studentFirstName, String studentLastName, String subjectName, BigDecimal gradeValue) {
        String formatFirstName = StringUtils.formatName(studentFirstName);
        String formatLastName = StringUtils.formatName(studentLastName);
        String formatSubjectName = StringUtils.formatName(subjectName);

        StudentValidator.validateName(formatFirstName);
        StudentValidator.validateName(formatLastName);
        Student student = findStudentOrThrow(formatFirstName, formatLastName);

        SubjectValidator.validateName(formatSubjectName);
        Subject subject = findSubjectOrThrow(formatSubjectName);

        GradeValidator.validateGrade(gradeValue);

        Grade newGrade = new Grade(student.getId(), subject.getId(), gradeValue);

        try {
            return gradeDAO.create(newGrade);
        } catch (SQLException e) {
            throw new GradeException("No se pudo registrar la nota.", e);
        }
    }

    public boolean update(int id, BigDecimal newGrade) {
        GradeValidator.validateId(id);
        GradeValidator.validateGrade(newGrade);

        try {
            return gradeDAO.update(id, newGrade);
        } catch (SQLException e) {
            throw new GradeException("No se pudo editar la nota.", e);
        }
    }

    public boolean delete(int id) {
        GradeValidator.validateId(id);

        try {
            return gradeDAO.delete(id);
        } catch (SQLException e) {
            throw new GradeException("No se pudo eliminar la nota.", e);
        }
    }

    public List<GradeView> getAll() {
        try {
            return gradeDAO.getAll();
        } catch (SQLException e) {
            throw new GradeException("No se pudieron cargar las notas.", e);
        }
    }

    public List<GradeView> getByStudentFullName(String firstName, String lastName) {
        String normalizedFirstName = StringUtils.normalizeForComparison(firstName);
        String normalizedLastName = StringUtils.normalizeForComparison(lastName);

        StudentValidator.validateName(normalizedFirstName);
        StudentValidator.validateName(normalizedLastName);

        try {
            return gradeDAO.getByStudentFullName(normalizedFirstName, normalizedLastName);
        } catch (SQLException e) {
            throw new GradeException("No se pudieron cargar las notas del estudiante.", e);
        }
    }

    public GradeView getById(int id) {
        GradeValidator.validateId(id);

        try {
            return gradeDAO.getById(id);
        } catch (SQLException e) {
            throw new GradeException("No se encontró la nota con ese id.", e);
        }
    }

    private Student findStudentOrThrow(String firstName, String lastName) {
        try {
            Student student = studentDAO.getByFirstAndLastName(firstName, lastName);
            if (student == null) {
                throw new GradeException("Estudiante no encontrado.");
            }
            return student;
        } catch (SQLException e) {
            throw new GradeException("Error al verificar la existencia del estudiante.", e);
        }
    }

    private Subject findSubjectOrThrow(String subjectName) {
        try {
            Subject subject = subjectDAO.getByName(subjectName);
            if (subject == null) {
                throw new GradeException("Materia no encontrada.");
            }
            return subject;
        } catch (SQLException e) {
            throw new GradeException("Error al verificar la existencia de la materia.", e);
        }
    }
}
