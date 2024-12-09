package fr.epita;

import fr.epita.services.data.QuizDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestQuizDataService {

    @Autowired
    QuizDataService quizDataService;

    //as a user I can create open questions
    @Test
    public void testOpenQuestionCreation(){
        //given: a question and a question data service

        //when
        this.quizDataService.createQuestion(question);

        //then

    }

    //REQ-0002: as a user I can create mcq questions



}
