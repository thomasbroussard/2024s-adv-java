package fr.epita;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.services.data.ChoiceJPADAO;
import fr.epita.services.data.QuestionJPADAO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Commit
public class TestQuestionJPADAO {

    @Autowired
    QuestionJPADAO dao;

    @Autowired
    ChoiceJPADAO choiceJPADAO;

    @Autowired
    DataSource ds;


    @Test
    @Transactional
    public void testEMTEST() throws SQLException {
        Question question = new Question("what is JPA?");

        dao.add(question);

        ResultSet resultSet = ds.getConnection()
                .prepareStatement("SELECT * FROM QUESTIONS")
                .executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("text"));
        }
    }

    @Test
    @Transactional
    public void testChoicePersitence() throws SQLException {
        Question question = new Question("what is JPA?");
        System.out.println(question.getId());
        dao.add(question);

        System.out.println(question.getId());
        Choice choice = new Choice();
        choice.setOrder(1);
        choice.setValid(true);
        choice.setOptionText("It means Java Persistence API");
        choice.setQuestionRef(question);

        choiceJPADAO.add(choice);

        ResultSet resultSet = ds.getConnection()
                .prepareStatement("SELECT * FROM CHOICES")
                .executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("OPTION_TEXT"));
        }
    }


}
