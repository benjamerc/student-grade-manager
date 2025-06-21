package com.studentgrades.validator;

public class SubjectValidator {

    public static void validateId(int id) {
        CommonValidator.validateId(id);
    }

    public static void validateName(String name) {
        CommonValidator.validateName(name, "El nombre de la materia");
    }
}
