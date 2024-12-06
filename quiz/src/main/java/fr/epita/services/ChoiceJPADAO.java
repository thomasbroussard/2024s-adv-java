package fr.epita.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;


public class ChoiceJPADAO extends GenericJPADAO<Choice>{

    // Static initializer to ensure the table is created
    public ChoiceJPADAO(EntityManager em) {
        super(em);
    }


    // Get all questions
    public List<Choice> listAll() {
        return em.createQuery("from Choice", Choice.class).getResultList();

    }

}
