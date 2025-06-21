package com.studentgrades.dao;

import com.studentgrades.config.DatabaseConnection;
import com.studentgrades.model.Grade;
import com.studentgrades.model.GradeView;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    public boolean create(Grade grade) throws SQLException {
        String sql = "INSERT INTO grades (student_id, subject_id, grade) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getSubjectId());
            preparedStatement.setBigDecimal(3, grade.getGrade());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public boolean update(int id, BigDecimal newGrade) throws SQLException {
        String sql = "UPDATE grades SET grade = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setBigDecimal(1, newGrade);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM grades WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected != 0;
        }
    }

    public List<GradeView> getAll() throws SQLException {
        String sql = """
                SELECT
                    grades.id,
                    CONCAT(students.first_name, ' ', students.last_name) AS studentFullName,
                    subjects.name AS subjectName,
                    grades.grade
                FROM grades
                INNER JOIN students ON students.id = grades.student_id
                INNER JOIN subjects ON subjects.id = grades.subject_id
                ORDER BY students.first_name
                """;

        List<GradeView> gradeViews = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                gradeViews.add(resultSetToGradeView(resultSet));
            }
        }

        return gradeViews;
    }

    public List<GradeView> getByStudentFullName(String firstName, String lastName) throws SQLException {
        String sql = """
                SELECT
                	grades.id,
                	CONCAT(students.first_name, ' ', students.last_name) AS studentFullName,
                	subjects.name AS subjectName,
                	grades.grade
                FROM grades
                INNER JOIN students ON students.id = grades.student_id
                INNER JOIN subjects ON subjects.id = grades.subject_id
                WHERE LOWER(TRIM(students.first_name)) = LOWER(TRIM(?))
                	AND LOWER(TRIM(students.last_name)) = LOWER(TRIM(?))
                """;

        List<GradeView> gradeViewList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    gradeViewList.add(resultSetToGradeView(resultSet));
                }
            }
        }

        return gradeViewList;
    }

    public GradeView getById(int id) throws SQLException {
        String sql = """
                SELECT
                    grades.id,
                    CONCAT(students.first_name, ' ', students.last_name) AS studentFullName,
                    subjects.name AS subjectName,
                    grades.grade
                FROM grades
                INNER JOIN students ON students.id = grades.student_id
                INNER JOIN subjects ON subjects.id = grades.subject_id
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToGradeView(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    private GradeView resultSetToGradeView(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String studentFullName = resultSet.getString("studentFullName");
        String subjectName = resultSet.getString("subjectName");
        double grade = resultSet.getDouble("grade");

        return new GradeView(id, studentFullName, subjectName, grade);
    }
}
