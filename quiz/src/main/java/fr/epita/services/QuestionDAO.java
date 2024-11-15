package fr.epita.services;

import fr.epita.quiz.datamodel.Question;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuestionDAO {
    private static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // Keeps the DB in memory as long as the app runs
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    // Connection acquisition method
    private static Connection getConnection() throws SQLException {
        Configuration configuration = null;
        try {
            configuration = new Configuration();
            String dbUrl = configuration.getValue("db.url");
            String dbUser = configuration.getValue("db.user");
            String dbPassword = configuration.getValue("db.pwd");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Static initializer to ensure the table is created
    static {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // Create table if it does not exist
            statement.execute("CREATE TABLE IF NOT EXISTS Question (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "text VARCHAR(255) NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a question
    public void addQuestion(Question question) {
        String query = "INSERT INTO Question (text) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, question.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Question";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                questions.add(new Question(id, text));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Delete a question by ID
    public void deleteQuestion(int id) {
        String query = "DELETE FROM Question WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
