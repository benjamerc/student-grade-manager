package com.studentgrades.util.menu;

import com.studentgrades.exception.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MenuMessagesLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = MenuMessagesLoader.class
                .getClassLoader()
                .getResourceAsStream("i18n/menu.properties")) {

            if (input == null) {
                throw new ConfigurationException("No se encontró el archivo menu.properties");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new ConfigurationException("Error al cargar menu.properties", e);
        }
    }

    public static Properties get() {
        return properties;
    }
}
