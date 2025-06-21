package com.studentgrades.service;

import com.studentgrades.exception.SubjectException;
import com.studentgrades.exception.ValidationException;
import com.studentgrades.manager.SubjectManager;
import com.studentgrades.model.Subject;

import java.util.List;
import java.util.Scanner;

public class SubjectService {

    private final Scanner scanner;

    private final SubjectManager subjectManager = new SubjectManager();

    public SubjectService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createSubject() {
        try {

            System.out.print("Ingrese el nombre de la materia: ");
            String subjectName = scanner.nextLine();

            boolean success = subjectManager.create(subjectName);

            if (success) {
                System.out.println("Materia registrada correctamente.");
            } else {
                System.out.println("No se pudo registrar la materia.");
            }

        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SubjectException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void getAllSubjects() {
        try {

            List<Subject> subjectList = subjectManager.getAll();

            if (subjectList.isEmpty()) {
                System.out.println("No hay materias registradas.");
            } else {
                subjectList.forEach(System.out::println);
            }

        } catch (SubjectException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void updateSubject() {
        try {

            System.out.print("Ingrese el id de la materia a editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el nuevo nombre de la materia: ");
            String newName = scanner.nextLine();

            boolean success = subjectManager.update(id, newName);

            if (success) {
                System.out.println("Materia editada correctamente.");
            } else {
                System.out.println("No se pudo editar la materia con ese id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SubjectException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void deleteSubject() {
        try {

            System.out.print("Ingrese el id de la materia a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            boolean success = subjectManager.delete(id);

            if (success) {
                System.out.println("Materia eliminada correctamente.");
            } else {
                System.out.println("No se pudo eliminar la materia con ese id.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresó un valor inválido. Intente de nuevo.");
        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SubjectException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
