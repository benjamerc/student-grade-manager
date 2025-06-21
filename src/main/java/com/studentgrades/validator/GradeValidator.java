package com.studentgrades.validator;

import com.studentgrades.exception.ValidationException;

import java.math.BigDecimal;

public class GradeValidator {

    public static void validateId(int id) {
        CommonValidator.validateId(id);
    }

    public static void validateGrade(BigDecimal grade) {
        if (grade == null) {
            throw new ValidationException("La nota no puede ser nula.");
        }

        if (grade.compareTo(BigDecimal.valueOf(1)) < 0 || grade.compareTo(BigDecimal.valueOf(10)) > 0) {
            throw new ValidationException("La nota debe estar entre 1 y 10.");
        }

        BigDecimal cleanGrade = grade.stripTrailingZeros();

        if (cleanGrade.scale() > 2) {
            throw new ValidationException("La nota debe tener como máximo 2 decimales.");
        }
    }
}
