package com.studentgrades.validator;

public class StudentValidator {

    public static void validateId(int id) {
        CommonValidator.validateId(id);
    }

    public static void validateName(String name) {
        CommonValidator.validateName(name, "El nombre y/o apellido del estudiante");
    }

}
