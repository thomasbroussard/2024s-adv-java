package fr.epita.services;

import fr.epita.quiz.datamodel.Question;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuestionDAO {

    DataSource ds;

    // Static initializer to ensure the table is created
    public QuestionDAO(@Autowired DataSource ds){
        this.ds = ds;
        try (Connection connection = ds.getConnection();
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
        try (Connection connection = ds.getConnection();
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
        try (Connection connection = ds.getConnection();
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
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
