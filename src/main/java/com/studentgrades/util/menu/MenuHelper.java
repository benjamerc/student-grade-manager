package com.studentgrades.util.menu;

import java.util.Properties;
import java.util.Scanner;
import java.util.function.IntConsumer;

public class MenuHelper {

    private final Scanner scanner;

    private static final Properties menuMessages = MenuMessagesLoader.get();

    public MenuHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu(String menuKey, IntConsumer optionProcessor, String exitMessage) {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(menuMessages.getProperty(menuKey));
            System.out.print("Ingrese una opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                optionProcessor.accept(option);

                if (option == 0) {
                    keepRunning = false;
                    if (exitMessage != null) {
                        System.out.println(exitMessage);
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("La opción debe ser un valor entero. Intente nuevamente.");
            }
        }
    }
}
