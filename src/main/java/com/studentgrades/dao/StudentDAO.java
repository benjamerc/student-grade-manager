package com.studentgrades.dao;

import com.studentgrades.config.DatabaseConnection;
import com.studentgrades.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean create(Student student) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public List<Student> getAll() throws SQLException {
        String sql = "SELECT * FROM students";

        List<Student> students = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                students.add(resultSetToStudent(resultSet));
            }
        }

        return students;
    }

    public Student getById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToStudent(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Student> getByNames(String columnLabel, String name) throws SQLException {
        String sql = "SELECT * FROM students WHERE LOWER(TRIM(" + columnLabel + ")) = LOWER(TRIM(?))";

        List<Student> students = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    students.add(resultSetToStudent(resultSet));
                }
            }
        }

        return students;
    }

    public Student getByFirstAndLastName(String firstName, String lastName) throws SQLException {
        String sql = "SELECT * FROM students WHERE first_name = ? AND last_name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToStudent(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean update(int id, String columnLabel, String newName) throws SQLException {
        String sql = "UPDATE students SET " + columnLabel + " = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public boolean existsByName(String firstName, String lastName) throws SQLException {
        String sql = """
            SELECT 1 FROM students
            WHERE LOWER(TRIM(first_name)) = LOWER(TRIM(?))
              AND LOWER(TRIM(last_name)) = LOWER(TRIM(?))
            LIMIT 1
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private Student resultSetToStudent(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        return new Student(id, firstName, lastName);
    }
}
