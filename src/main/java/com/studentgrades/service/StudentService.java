package com.studentgrades.service;

import com.studentgrades.exception.StudentException;
import com.studentgrades.exception.ValidationException;
import com.studentgrades.manager.StudentManager;
import com.studentgrades.model.Student;
import com.studentgrades.validator.StudentFields;

import java.util.List;
import java.util.Scanner;

public class StudentService {

    private final Scanner scanner;

    private final StudentManager studentManager = new StudentManager();

    public StudentService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createStudent() {
        try {

            System.out.print("Ingrese el nombre del estudiante: ");
            String firstName = scanner.nextLine();

            System.out.print("Ingrese el apellido del estudiante: ");
            String lastName = scanner.nextLine();

            boolean success = studentManager.create(firstName, lastName);

            if (success) {
                System.out.println("Estudiante registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el estudiante.");
            }

        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void getAllStudents() {
        try {

            List<Student> studentList = studentManager.getAll();

            if (studentList.isEmpty()) {
                System.out.println("No hay estudiantes registrados.");
            } else {
                studentList.forEach(System.out::println);
            }

        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void getStudentsByFirstName() {
        getStudentsByField(StudentFields.FIRST_NAME, "Ingrese el nombre del estudiante: ",
                "No se encontraron estudiantes con ese nombre.");
    }

    public void getStudentsByLastName() {
        getStudentsByField(StudentFields.LAST_NAME, "Ingrese el apellido del estudiante: ",
                "No se encontraron estudiantes con ese apellido.");
    }

    public void getStudentByFirstAndLastName() {
        try {

            System.out.print("Ingrese el nombre del estudiante: ");
            String firstName = scanner.nextLine();

            System.out.print("Ingrese el apellido del estudiante: ");
            String lastName = scanner.nextLine();

            Student student = studentManager.getByFirstAndLastName(firstName, lastName);

            if (student == null) {
                System.out.println("No hay ningún estudiante registrado con ese nombre y apellido.");
            } else {
                System.out.println(student);
            }

        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void updateStudentFirstName() {
        updateStudentByField(StudentFields.FIRST_NAME);
    }

    public void updateStudentLastName() {
        updateStudentByField(StudentFields.LAST_NAME);
    }

    public void deleteStudent() {
        try {

            System.out.print("Ingrese el id dele estudiante a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            boolean success = studentManager.delete(id);

            if (success) {
                System.out.println("Estudiante eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el estudiante con ese id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void getStudentsByField(StudentFields field, String prompt, String notFoundMessage) {
        try {
            System.out.print(prompt);
            String value = scanner.nextLine();

            List<Student> studentList = studentManager.getByNames(field.getFieldName(), value);

            if (studentList.isEmpty()) {
                System.out.println(notFoundMessage);
            } else {
                studentList.forEach(System.out::println);
            }

        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void updateStudentByField(StudentFields field) {
        try {

            System.out.print("Ingrese el id del estudiante a editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el nuevo " + field.getFieldNameSpanish() + ": ");
            String newValue = scanner.nextLine();

            boolean success = studentManager.update(id, field, newValue);

            if (success) {
                System.out.println("Estudiante editado correctamente.");
            } else {
                System.out.println("No se pudo editar el estudiante con ese id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
