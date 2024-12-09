package fr.epita.services.data;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;

import java.util.List;

public class QuizDataService {

    QuestionJPADAO questionDAO;
    ChoiceDAO choiceDAO;

    public QuizDataService(){

    }

    //create a text question
    public void createQuestion(Question question){
        questionDAO.add(question);
    }

    //create a MCQ question
    public void createMCQQuestion(Question question, List<Choice> choiceList){

    }

}
