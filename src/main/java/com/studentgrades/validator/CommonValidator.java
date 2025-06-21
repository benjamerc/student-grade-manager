package com.studentgrades.validator;

import com.studentgrades.exception.ValidationException;

public class CommonValidator {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static void validateId(int id) {
        if (id < 1) {
            throw new ValidationException("El id no debe ser menor a 1.");
        }

        if (id > 1_000_000) {
            throw new ValidationException("El id no debe superar 1.000.000.");
        }
    }

    public static void validateName(String name, String fieldDescription) {
        if (name == null) {
            throw new ValidationException(fieldDescription + " no puede ser nulo.");
        }

        if (isEmpty(name)) {
            throw new ValidationException(fieldDescription + " no puede estar vacío.");
        }

        if (name.length() > 50) {
            throw new ValidationException(fieldDescription + " no puede superar los 50 caracteres.");
        }

        if (name.length() < 2) {
            throw new ValidationException(fieldDescription + " debe tener al menos 2 caracteres.");
        }

        if (!name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
            throw new ValidationException(fieldDescription + " solo debe contener letras y espacios.");
        }
    }
}
