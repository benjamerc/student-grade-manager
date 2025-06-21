package com.studentgrades.config;

import com.studentgrades.exception.ConfigurationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final Properties config = DatabaseConfigLoader.get();

    private static final String URL = config.getProperty("db.url");
    private static final String USER = config.getProperty("db.user");
    private static final String PASSWORD = config.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new ConfigurationException("Variables de entorno DB_URL, DB_USER o DB_PASSWORD no están definidas.");
        }

        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("No se pudo conectar a la base de datos.", e);
        }

        return connection;
    }
}
