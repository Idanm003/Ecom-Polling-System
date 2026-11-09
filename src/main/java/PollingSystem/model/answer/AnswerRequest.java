package PollingSystem.model.answer;

public class AnswerRequest {
    private Integer userId;
    private Integer questionId;
    private Integer selectedOption;

    public AnswerRequest(Integer userId, Integer questionId, Integer selectedOption) {
        this.userId = userId;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    public AnswerRequest() {}

    public Integer getUserId() {
        return this.userId;
    }
    public Integer getQuestionId() {
        return this.questionId;
    }
    public Integer getSelectedOption() {
        return this.selectedOption;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Answer toAnswer() {
        return new Answer(
                null,
                this.userId,
                this.questionId,
                this.selectedOption
        );
    }
}
