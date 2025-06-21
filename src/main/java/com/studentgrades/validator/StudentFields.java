package com.studentgrades.validator;

import com.studentgrades.exception.ValidationException;

public enum StudentFields {

    FIRST_NAME("first_name", "nombre"),
    LAST_NAME("last_name", "apellido");

    private final String fieldName;
    private final String fieldNameSpanish;

    StudentFields(String fieldName, String fieldNameSpanish) {
        this.fieldName = fieldName;
        this.fieldNameSpanish = fieldNameSpanish;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getFieldNameSpanish() {
        return this.fieldNameSpanish;
    }

    public static StudentFields fromString(String text) {
        for (StudentFields field : StudentFields.values()) {
            if (field.getFieldName().equals(text)) {
                return field;
            }
        }
        throw new ValidationException("Campo no editable o inexistente: " + text);
    }
}
