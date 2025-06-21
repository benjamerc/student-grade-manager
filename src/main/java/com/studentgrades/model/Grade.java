package com.studentgrades.model;

import java.math.BigDecimal;

public class Grade {

    private int id;
    private int studentId;
    private int subjectId;
    private BigDecimal grade;

    public Grade() {}

    public Grade(int id, int studentId, int subjectId, BigDecimal grade) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
    }

    public Grade(int studentId, int subjectId, BigDecimal grade) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        if (grade.compareTo(BigDecimal.valueOf(1)) < 0 || grade.compareTo(BigDecimal.valueOf(10)) > 0) {
            throw new IllegalArgumentException("La nota debe estar entre 1 y 10.");
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format(
                "Grade{id=%d, studentId=%d, subjectId=%d, grade=%.2f}",
                id, studentId, subjectId, grade
        );
    }
}
