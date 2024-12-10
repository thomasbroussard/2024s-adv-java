package fr.epita.quiz.rest;

import fr.epita.quiz.datamodel.Question;
import fr.epita.services.data.QuizDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {

    @Autowired
    QuizDataService quizDataService;

    @GetMapping
    public String getQuestions(){
        List<Question> allQuestions = quizDataService.getAllQuestions();

        return String.valueOf(allQuestions);
    }

    @GetMapping(path = "/{id}")
    public String getQuestions(@PathVariable("id") String questionId){
      //  quizDataService.findQuestionById(questionId);
        return "hello";
    }


}
