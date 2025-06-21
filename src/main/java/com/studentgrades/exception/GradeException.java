package com.studentgrades.exception;

public class GradeException extends RuntimeException {
    public GradeException(String message) {
        super(message);
    }

  public GradeException(String message, Throwable cause) {
    super(message, cause);
  }
}
