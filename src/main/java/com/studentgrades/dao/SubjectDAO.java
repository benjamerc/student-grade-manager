package com.studentgrades.dao;

import com.studentgrades.config.DatabaseConnection;
import com.studentgrades.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public boolean create(Subject subject) throws SQLException {
        String sql = "INSERT INTO subjects (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subject.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public List<Subject> getAll() throws SQLException {
        String sql = "SELECT * FROM subjects ORDER BY name";

        List<Subject> subjects = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                subjects.add(resultSetToSubject(resultSet));
            }
        }

        return subjects;
    }

    public Subject getById(int id) throws SQLException {
        String sql = "SELECT * FROM subjects WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToSubject(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public Subject getByName(String name) throws SQLException {
        String sql = "SELECT * FROM subjects WHERE LOWER(TRIM(name)) = LOWER(TRIM(?))";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToSubject(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean update(int id, String name) throws SQLException {
        String sql = "UPDATE subjects SET name = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM subjects WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public boolean exists(String name) throws SQLException {
        String sql = """
            SELECT 1 FROM subjects
            WHERE LOWER(TRIM(name)) = LOWER(TRIM(?))
            LIMIT 1
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private Subject resultSetToSubject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Subject(id, name);
    }
}
