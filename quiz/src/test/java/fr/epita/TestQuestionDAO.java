package fr.epita;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.services.data.ChoiceDAO;
import fr.epita.services.data.QuestionDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Disabled("need update")
public class TestQuestionDAO {


    @Autowired
    @Qualifier("test")
    QuestionDAO questionDAO;

    @Autowired
    ChoiceDAO choiceDAO;


    @Test
    public void testDAO(){
        //given
        Question question = new Question("what is a singleton?");

        //when
        questionDAO.addQuestion(question);
        List<Question> allQuestions = questionDAO.getAllQuestions();

        //then
        Assertions.assertThat(allQuestions).hasSize(1);
        Assertions.assertThat(allQuestions.get(0).getText()).isEqualTo("what is a singleton?");

    }
    @Test
    public void testQuestionAndChoice(){
        //given
        Question question = new Question("what is a singleton?");

        //when
        choiceDAO.add(new Choice());
        List<Question> allQuestions = questionDAO.getAllQuestions();

        //then
        Assertions.assertThat(allQuestions).hasSize(1);
        Assertions.assertThat(allQuestions.get(0).getText()).isEqualTo("what is a singleton?");

    }


}
