package com.studentgrades.service;

import com.studentgrades.exception.GradeException;
import com.studentgrades.exception.ValidationException;
import com.studentgrades.manager.GradeManager;
import com.studentgrades.model.GradeView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class GradeService {

    private final Scanner scanner;

    private final GradeManager gradeManager = new GradeManager();

    public GradeService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createGrade() {
        try {

            System.out.print("Ingrese el nombre del estudiante: ");
            String studentFirstName = scanner.nextLine();

            System.out.print("Ingrese el apellido del estudiante: ");
            String studentLastName = scanner.nextLine();

            System.out.print("Ingrese el nombre de la materia: ");
            String subjectName = scanner.nextLine();

            System.out.print("Ingrese la nota: ");
            String input = scanner.nextLine();

            BigDecimal grade = new BigDecimal(input);

            boolean success = gradeManager.create(studentFirstName, studentLastName, subjectName, grade);

            if (success) {
                System.out.println("Nota registrada correctamente.");
            } else {
                System.out.println("No se pudo registrar la nota.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (GradeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void updateGrade() {
        try {

            System.out.print("Ingrese el id de la nota a editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese la nueva nota: ");
            String input = scanner.nextLine();

            BigDecimal grade = new BigDecimal(input);

            boolean success = gradeManager.update(id, grade);

            if (success) {
                System.out.println("Nota editada correctamente.");
            } else {
                System.out.println("No se pudo editar la nota con ese id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (GradeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void deleteGrade() {
        try {

            System.out.print("Ingrese el id de la nota a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            boolean success = gradeManager.delete(id);

            if (success) {
                System.out.println("Se eliminó la nota correctamente.");
            } else {
                System.out.println("No se pudo eliminar la nota con ese id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (GradeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void getAllGrades() {
        try {

            List<GradeView> gradeViewList = gradeManager.getAll();

            if (gradeViewList.isEmpty()) {
                System.out.println("No hay notas registradas.");
            } else {
                gradeViewList.forEach(System.out::println);
            }

        } catch (GradeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void getGradeByStudentFullName() {
        try {

            System.out.print("Ingrese el nombre del estudiante: ");
            String firstName = scanner.nextLine();

            System.out.print("Ingrese el apellido del estudiante: ");
            String lastName = scanner.nextLine();

            List<GradeView> gradeViewList = gradeManager.getByStudentFullName(firstName, lastName);

            if (gradeViewList.isEmpty()) {
                System.out.println("No se encontraron notas de ese estudiante.");
            } else {
                gradeViewList.forEach(System.out::println);
            }

        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (GradeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
