package com.studentgrades.model;

public class Student {

    private int id;
    private String firstName;
    private String lastName;

    public Student() {}

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return String.format("""
                --------------------------
                ID: %d
                Nombre: %s
                Apellido: %s
                """,
                this.id,
                this.firstName != null ? this.firstName : "(sin nombre definido)",
                this.lastName != null ? this.lastName : "(sin apellido definido)");
    }
}
