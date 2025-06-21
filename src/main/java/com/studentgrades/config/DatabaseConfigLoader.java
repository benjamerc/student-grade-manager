package com.studentgrades.config;

import com.studentgrades.exception.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfigLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config/database-config.properties")) {

            if (input == null) {
                throw new ConfigurationException("No se encontró el archivo database-config.properties.");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new ConfigurationException("Error al cargar database-config.properties", e);
        }
    }

    public static Properties get() {
        return properties;
    }
}
