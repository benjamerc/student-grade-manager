package com.studentgrades.model;

public class GradeView {

    private int id;
    private String studentFullName;
    private String subjectName;
    private double grade;

    public GradeView() {}

    public GradeView(int id, String studentFullName, String subjectName, double grade) {
        this.id = id;
        this.studentFullName = studentFullName;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public GradeView(String studentFullName, String subjectName, double grade) {
        this.studentFullName = studentFullName;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("""
                --------------------------
                ID: %d
                Estudiante: %s
                Materia: %s
                Nota: %.2f
                """, id, studentFullName, subjectName, grade);
    }
}
