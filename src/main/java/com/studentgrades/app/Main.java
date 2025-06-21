package com.studentgrades.app;

import com.studentgrades.exception.ConfigurationException;
import com.studentgrades.service.MenuService;

public class Main {

    public static void main(String[] args) {
        try {

            MenuService app = new MenuService();
            app.run();

            System.exit(0);

        } catch (ConfigurationException e) {
            System.out.println("Error crítico de configuración: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            System.exit(2);
        }
    }
}
