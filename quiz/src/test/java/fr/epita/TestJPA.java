package fr.epita;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public void testEMTEST() throws SQLException {
        Question question = new Question("what is JPA?");

        em.persist(question);


        ResultSet resultSet = ds.getConnection()
                .prepareStatement("SELECT * FROM QUESTIONS")
                .executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("title"));
        }


    }


}
