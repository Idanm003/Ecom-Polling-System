package PollingSystem.responses.answers;

public class AnswerDetailsResponse {
    private Integer questionId;
    private String questionTitle;
    private Integer selectedOption;
    private String selectedOptionText;

    public AnswerDetailsResponse(Integer questionId, String questionTitle, Integer selectedOption, String selectedOptionText) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.selectedOption = selectedOption;
        this.selectedOptionText = selectedOptionText;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }
    public String getQuestionTitle() {
        return this.questionTitle;
    }
    public Integer getSelectedOption() {
        return this.selectedOption;
    }
    public String getSelectedOptionText() {
        return this.selectedOptionText;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }
    public void setSelectedOptionText(String selectedOptionText) {
        this.selectedOptionText = selectedOptionText;
    }
}
