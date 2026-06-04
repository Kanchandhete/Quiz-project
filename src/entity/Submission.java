package entity;

public class Submission {
    private String studentName;
    private int score;
    private float percentage;
    private int timeSeconds;

    public Submission() {
    }

    public Submission(String studentName, int score, float percentage, int timeSeconds) {
        this.studentName = studentName;
        this.score = score;
        this.percentage = percentage;
        this.timeSeconds = timeSeconds;
    }    

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getTimeSeconds() {
        return timeSeconds;
    }
      
}
