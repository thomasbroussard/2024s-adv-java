package fr.epita;

import fr.epita.quiz.datamodel.Question;
import fr.epita.services.QuestionDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestQuestionDAO {

    @Test
    public void testDAO(){
        //given
        Question question = new Question("what is a singleton?");

        //when
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.addQuestion(question);
        List<Question> allQuestions = questionDAO.getAllQuestions();

        //then
        Assertions.assertThat(allQuestions).hasSize(1);
        Assertions.assertThat(allQuestions.get(0).getText()).isEqualTo("what is a singleton?");

    }


}
