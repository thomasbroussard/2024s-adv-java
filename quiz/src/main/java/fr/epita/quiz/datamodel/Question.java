package fr.epita.quiz.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "QUESTIONS")
public class Question {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Primary key

    @Column(name="TEXT")
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
