package fr.epita;

import fr.epita.services.data.QuizDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class TestQuizDataService {

    @Autowired
    QuizDataService quizDataService;

    //REQ0001 - as a user I can create open questions
    @Test
    public void testOpenQuestionCreation(){
        //given: a question and a question data service


        //when
        this.quizDataService.createQuestion(question);

        //then

    }

    //REQ-0002: as a user I can create mcq questions



}
