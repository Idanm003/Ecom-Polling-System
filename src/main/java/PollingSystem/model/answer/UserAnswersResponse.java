package PollingSystem.model.answer;

import PollingSystem.model.user.User;

import java.util.List;

public class UserAnswersResponse {
    private User user;
    private List<AnswerDetailsResponse> answers;

    public UserAnswersResponse(User user, List<AnswerDetailsResponse> answers) {
        this.user = user;
        this.answers = answers;
    }

    public User getUser() {
        return this.user;
    }
    public List<AnswerDetailsResponse> getAnswers() {
        return this.answers;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setAnswers(List<AnswerDetailsResponse> answers) {
        this.answers = answers;
    }
}
