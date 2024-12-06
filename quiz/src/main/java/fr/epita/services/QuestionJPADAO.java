package fr.epita.services;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;


public class QuestionJPADAO extends GenericJPADAO<Question, Integer> {

    EntityManager em;

    // Static initializer to ensure the table is created
    public QuestionJPADAO(EntityManager em) {
        super(em);
    }


    // Get all questions
    public List<Question> listAll() {
        return em.createQuery("from Question", Question.class).getResultList();
    }

}

