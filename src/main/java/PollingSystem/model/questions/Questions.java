package PollingSystem.model.questions;

public class Questions {
    private Integer questionId;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public Questions() {}

    public Questions(Integer questionId, String questionTitle, String option1, String option2, String option3, String option4) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public String getOptionText(Integer optionNumber) {
        return switch (optionNumber) {
            case 1 -> this.option1;
            case 2 -> this.option2;
            case 3 -> this.option3;
            case 4 -> this.option4;
            default -> null;
        };
    }

    public Integer getQuestionId() {
        return this.questionId;
    }
    public String getQuestionTitle() {
        return this.questionTitle;
    }
    public String getOption1() {
        return this.option1;
    }
    public String getOption2() {
        return this.option2;
    }
    public String getOption3() {
        return this.option3;
    }
    public String getOption4() {
        return this.option4;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public void setOption4(String option4) {
        this.option4 = option4;
    }
}
