package fr.epita.services;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;


public class QuestionJPADAO {

    EntityManager em;

    // Static initializer to ensure the table is created
    public QuestionJPADAO(EntityManager em) {
        this.em = em;
    }

    // Add a question
    @Transactional
    public void add(Question question) {
        em.persist(question);
    }

    // Get all questions
    public List<Question> listAll() {
        return em.createQuery("from Question", Question.class).getResultList();

    }

    // Delete a question by ID
    @Transactional
    public void deleteById(int id) {
        Question question = em.find(Question.class, id);
        em.remove(question);
    }
}
