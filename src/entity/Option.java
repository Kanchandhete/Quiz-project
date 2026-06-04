package entity;

public class Option {
    private int id;
    private int questionId;
    private String text;
    private boolean isCorrect;

    public Option() {
    }

    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getText() {
        return text;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
}
