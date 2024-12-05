package fr.epita;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
public class TestJPA {

    @PersistenceContext
    EntityManager em;

    @Autowired
    DataSource ds;


    @Test
    @Transactional
    public void testEMTEST() throws SQLException {
        Question question = new Question("what is JPA?");

        em.persist(question);

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
        em.persist(question);

        System.out.println(question.getId());
        Choice choice = new Choice();
        choice.setOrder(1);
        choice.setValid(true);
        choice.setOptionText("It means Java Persistence API");
        choice.setQuestionRef(question);

        em.persist(choice);

        ResultSet resultSet = ds.getConnection()
                .prepareStatement("SELECT * FROM CHOICES")
                .executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("OPTION_TEXT"));
        }
    }


}
