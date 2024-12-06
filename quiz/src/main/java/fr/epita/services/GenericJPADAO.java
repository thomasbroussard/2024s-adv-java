package fr.epita.services;

import fr.epita.quiz.datamodel.Choice;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

public class GenericJPADAO<T> {

    EntityManager em;

    public GenericJPADAO(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void add(T entityInstance) {
        em.persist(entityInstance);
    }

    @Transactional
    public void update(T entityInstance) {
        em.merge(entityInstance);
    }


    // Delete a question by ID
    @Transactional
    public void delete(T entityInstance) {
        em.remove(entityInstance);
    }

}
