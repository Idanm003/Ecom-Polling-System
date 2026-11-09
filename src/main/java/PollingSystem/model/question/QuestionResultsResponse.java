package PollingSystem.model.question;

import java.util.Map;

public class QuestionResultsResponse {
    private Integer questionId;
    private String questionTitle;
    private Map<String, Integer> optionsCount;
    private Integer totalAnswers;

    public QuestionResultsResponse(Integer questionId, String questionTitle, Map<String, Integer> optionsCount, Integer totalAnswers) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.optionsCount = optionsCount;
        this.totalAnswers = totalAnswers;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }
    public String getQuestionTitle() {
        return this.questionTitle;
    }
    public Map<String, Integer> getOptionsCount() {
        return this.optionsCount;
    }
    public Integer getTotalAnswers() {
        return this.totalAnswers;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    public void setOptionsCount(Map<String, Integer> optionsCount) {
        this.optionsCount = optionsCount;
    }
    public void setTotalAnswers(Integer totalAnswers) {
        this.totalAnswers = totalAnswers;
    }
}
