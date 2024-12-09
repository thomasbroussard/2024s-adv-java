package fr.epita.services.data;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoiceDAO {

    private final DataSource ds;

    public ChoiceDAO(@Autowired DataSource ds) {
        this.ds = ds;

        // Create the Choices table if it does not exist
        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Choice (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "optionText VARCHAR(255) NOT NULL, " +
                    "isValid BOOLEAN NOT NULL, " +
                    "choiceOrder INT NOT NULL, " +
                    "questionId INT NOT NULL, " +
                    "FOREIGN KEY (questionId) REFERENCES Question(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Choice choice) {
        String query = "INSERT INTO Choice (optionText, isValid, choiceOrder, questionId) VALUES (?, ?, ?, ?)";
        try (Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, choice.getOptionText());
            preparedStatement.setBoolean(2, choice.isValid());
            preparedStatement.setInt(3, choice.getOrder());
            preparedStatement.setInt(4, choice.getQuestionRef().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Choice choice) {
        String query = "UPDATE Choice SET optionText = ?, isValid = ?, choiceOrder = ?, questionId = ? WHERE id = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, choice.getOptionText());
            preparedStatement.setBoolean(2, choice.isValid());
            preparedStatement.setInt(3, choice.getOrder());
            preparedStatement.setInt(4, choice.getQuestionRef().getId());
            preparedStatement.setInt(5, choice.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Choice choice) {
        String query = "DELETE FROM Choice WHERE id = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, choice.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Choice> getChoicesForQuestion(Choice choiceFilter) {
        List<Choice> choices = new ArrayList<>();
        String query = "SELECT * FROM Choice WHERE questionId = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, choiceFilter.getQuestionRef().getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Choice choice = new Choice();
                    choice.setId(resultSet.getInt("id"));
                    choice.setOptionText(resultSet.getString("optionText"));
                    choice.setValid(resultSet.getBoolean("isValid"));
                    choice.setOrder(resultSet.getInt("choiceOrder"));

                    // Associate the choice with the parent question
                    Question question = new Question();
                    question.setId(resultSet.getInt("questionId"));
                    choice.setQuestionRef(question);

                    choices.add(choice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choices;
    }
}
