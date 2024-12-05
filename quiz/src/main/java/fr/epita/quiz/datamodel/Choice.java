package fr.epita.quiz.datamodel;


import jakarta.persistence.*;

@Entity
@Table(name="CHOICES")
public class Choice {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "OPTION_TEXT")
    private String optionText;

    @Column(name = "VALID")
    private boolean valid;

    @Column(name = "displayOrder")
    private int order;

    @ManyToOne
    private Question questionRef;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Question getQuestionRef() {
        return questionRef;
    }

    public void setQuestionRef(Question questionRef) {
        this.questionRef = questionRef;
    }
}
