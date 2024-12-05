package fr.epita.services;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuestionJPADAO {

    EntityManager em;

    // Static initializer to ensure the table is created
    public QuestionJPADAO(EntityManager em){
        this.em = em;
    }

    // Add a question
    @Transactional
    public void addQuestion(Question question) {
       em.persist(question);
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
