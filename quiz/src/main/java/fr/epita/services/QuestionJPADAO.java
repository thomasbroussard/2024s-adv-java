package fr.epita.services;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuestionJPADAO {

    EntityManager em;

    // Static initializer to ensure the table is created
    public QuestionJPADAO(EntityManager em) {
        this.em = em;
    }

    // Add a question
    @Transactional
    public void addQuestion(Question question) {
        em.persist(question);
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        return em.createQuery("from Question", Question.class).getResultList();

    }

    // Delete a question by ID
    @Transactional
    public void deleteQuestion(int id) {
        Question question = em.find(Question.class, id);
        em.remove(question);
    }
}
