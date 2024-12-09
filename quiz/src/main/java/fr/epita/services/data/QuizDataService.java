package fr.epita.services.data;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;

import java.util.List;

public class QuizDataService {

    QuestionJPADAO questionDAO;
    ChoiceJPADAO choiceDAO;

    public QuizDataService(){

    }

    public QuizDataService(QuestionJPADAO questionJPADAO, ChoiceJPADAO choiceJPADAO) {
        this.questionDAO = questionJPADAO;
        this.choiceDAO = choiceJPADAO;
    }

    //create a text question
    public void createQuestion(Question question){
        questionDAO.add(question);
    }

    //create a MCQ question
    public void createMCQQuestion(Question question, List<Choice> choiceList){

    }

}
