package fr.epita.services.data;

import fr.epita.quiz.datamodel.Choice;
import jakarta.persistence.EntityManager;

import java.util.List;


public class ChoiceJPADAO extends GenericJPADAO<Choice, Integer>{

    // Static initializer to ensure the table is created
    public ChoiceJPADAO(EntityManager em) {
        super(em);
    }
    // Get all questions
    public List<Choice> listAll() {
        return em.createQuery("from Choice", Choice.class).getResultList();

    }

}
