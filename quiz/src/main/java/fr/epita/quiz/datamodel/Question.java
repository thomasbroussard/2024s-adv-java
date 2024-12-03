package fr.epita.quiz.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table("QUESTIONS")
public class Question {

    @Id
    private int id;       // Primary key
    private String text;  // Question text

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(String text) {
        this.text = text;
    }
    public Question() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
