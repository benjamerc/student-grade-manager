package com.studentgrades.service;

import com.studentgrades.util.menu.MenuHelper;
import com.studentgrades.util.menu.MenuKeys;

import java.util.Scanner;

public class MenuService {

    private static final String INVALID_OPTION = "Opción incorrecta / inexistente.";

    private final Scanner scanner = new Scanner(System.in);

    private final SubjectService subjectService = new SubjectService(scanner);
    private final StudentService studentService = new StudentService(scanner);
    private final GradeService gradeService = new GradeService(scanner);

    private final MenuHelper menuHelper = new MenuHelper(scanner);

    public void run() {
        menuHelper.showMenu(MenuKeys.MAIN_MENU, this::processMainMenuOption, "Saliendo del sistema...");
    }

    private void showSubjectMenu() {
        menuHelper.showMenu(MenuKeys.SUBJECT_MENU, this::processSubjectMenuOption, "Volviendo al menú principal...");
    }

    private void showStudentMenu() {
        menuHelper.showMenu(MenuKeys.STUDENT_MENU, this::processStudentMenuOption, "Volviendo al menú principal...");
    }

    private void showStudentSearchMenu() {
        menuHelper.showMenu(MenuKeys.STUDENT_SEARCH_MENU, this::processStudentSearchMenuOption, "Volviendo...");
    }

    private void showStudentEditMenu() {
        menuHelper.showMenu(MenuKeys.STUDENT_EDIT_MENU, this::processStudentEditMenuOption, "Volviendo...");
    }

    private void showGradesMenu() {
        menuHelper.showMenu(MenuKeys.GRADES_MENU, this::processGradesMenuOption, "Volviendo al menú principal...");
    }

    private void processMainMenuOption(int option) {
        switch (option) {
            case 1 -> showSubjectMenu();
            case 2 -> showStudentMenu();
            case 3 -> showGradesMenu();
            case 0 -> {}
            default -> System.out.println(INVALID_OPTION);
        }
    }

    private void processSubjectMenuOption(int option) {
        switch (option) {
            case 1 -> subjectService.createSubject();
            case 2 -> subjectService.getAllSubjects();
            case 3 -> subjectService.updateSubject();
            case 4 -> subjectService.deleteSubject();
            case 0 -> {}
            default -> System.out.println(INVALID_OPTION);
        }
    }

    private void processStudentMenuOption(int option) {
        switch (option) {
            case 1 -> studentService.createStudent();
            case 2 -> studentService.getAllStudents();
            case 3 -> showStudentSearchMenu();
            case 4 -> showStudentEditMenu();
            case 5 -> studentService.deleteStudent();
            case 0 -> {}
            default -> System.out.println(INVALID_OPTION);
        }
    }

    private void processStudentSearchMenuOption(int option) {
        switch (option) {
            case 1 -> studentService.getStudentsByFirstName();
            case 2 -> studentService.getStudentsByLastName();
            case 3 -> studentService.getStudentByFirstAndLastName();
            case 0 -> {}
            default -> System.out.println(INVALID_OPTION);
        }
    }

    private void processStudentEditMenuOption(int option) {
        switch (option) {
            case 1 -> studentService.updateStudentFirstName();
            case 2 -> studentService.updateStudentLastName();
            case 0 -> {}
            default -> System.out.println(INVALID_OPTION);
        }
    }

    private void processGradesMenuOption(int option) {
        switch (option) {
            case 1 -> gradeService.createGrade();
            case 2 -> gradeService.getAllGrades();
            case 3 -> gradeService.getGradeByStudentFullName();
            case 4 -> gradeService.updateGrade();
            case 5 -> gradeService.deleteGrade();
            case 0 -> {}
            default -> System.out.println(INVALID_OPTION);
        }
    }
}
