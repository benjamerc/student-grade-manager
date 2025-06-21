package com.studentgrades.model;

public class Subject {

    private int id;
    private String name;

    public Subject() {}

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("""
                --------------------------
                ID: %d
                Materia: %s
                """,
                this.id,
                this.name != null ? this.name : "(sin nombre definido)");
    }
}
