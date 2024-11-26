package fr.epita.quiz.datamodel;

public class Choice {

    private int id;
    private String optionText;
    private boolean valid;

    private int order;

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
