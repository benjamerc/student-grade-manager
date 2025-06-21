package com.studentgrades.exception;

public class SubjectException extends RuntimeException {
    public SubjectException(String message) {
        super(message);
    }

    public SubjectException(String message, Throwable cause) {
      super(message, cause);
    }
}
