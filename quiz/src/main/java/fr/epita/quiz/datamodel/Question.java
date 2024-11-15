package fr.epita.quiz.datamodel;

public class Question {
    private int id;       // Primary key
    private String text;  // Question text

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(String text) {
        this.text = text;
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
