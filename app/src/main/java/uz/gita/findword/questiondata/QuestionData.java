package uz.gita.findword.questiondata;

public class QuestionData {
    private String question;
    private String answer;
    private String variant;

    public QuestionData(String question, String answer, String variant) {
        this.question = question;
        this.answer = answer;
        this.variant = variant;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getVariant() {
        return variant;
    }
}
