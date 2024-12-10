package fr.epita;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.services.data.QuizDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class TestQuizDataService {

    @Autowired
    QuizDataService quizDataService;

    @Autowired
    DataSource dataSource;

    //REQ- 0001 - as a user I can create open questions
    //FEATURE-0001 from a question model, when i call the insertion function, i should see the entry in the database
    @Test
    public void testOpenQuestionCreation() throws SQLException {
        //given: a question and a question data service
        Question question = new Question("What is JPA?");

        //when
        this.quizDataService.createQuestion(question);

        //then
        ResultSet resultSet = dataSource.getConnection()
                .prepareStatement("SELECT * FROM QUESTIONS")
                .executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("text"));
        }

    }

    //REQ-0002: as a user I can create mcq questions
    @Test
    public void testMCQQuestionCreation() throws SQLException {
        //given: a question and a question data service
        Question question = new Question("What is JPA in the context of Java Programming Language?");
        Choice validChoice = new Choice();
        validChoice.setOptionText("it's Java Persistence API");
        validChoice.setValid(true);
        validChoice.setOrder(1);

        Choice invalidChoice = new Choice();
        invalidChoice.setOptionText("it's Java Proprietary Interface");
        invalidChoice.setValid(false);
        invalidChoice.setOrder(2);

        //when
        this.quizDataService.createMCQQuestion(question, List.of(validChoice, invalidChoice));

        //then
        ResultSet resultSet = dataSource.getConnection()
                .prepareStatement("SELECT * FROM QUESTIONS")
                .executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("text"));
        }

    }

}
