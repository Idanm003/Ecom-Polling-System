package PollingSystem.model.answers;

public class Answers {
    private Integer answerId;
    private Integer userId;
    private Integer questionId;
    private Integer selectedOption;
    
    public Answers(Integer answerId, Integer userId, Integer questionId, Integer selectedOption) {
        this.answerId = answerId;
        this.userId = userId;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }
    public Answers() {}

    public Integer getAnswerId() {
        return this.answerId;
    }
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSelectedOption() {
        return this.selectedOption;
    }
    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }
}
