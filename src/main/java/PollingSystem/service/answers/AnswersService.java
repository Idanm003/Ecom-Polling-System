package PollingSystem.service.answers;

import PollingSystem.requests.answers.AnswerRequest;
import PollingSystem.responses.questions.QuestionResultsResponse;
import PollingSystem.responses.answers.UserAnswersResponse;

import java.util.List;

public interface AnswersService {
    QuestionResultsResponse getOptionsCountByQuestionId(Integer questionId) throws Exception;
    Integer getAnswersCountByQuestionId(Integer questionId) throws Exception;
    Integer getAnswersCountByUserId(Integer userId) throws Exception;
    UserAnswersResponse getAllAnswersByUserId(Integer userId) throws Exception;
    List<QuestionResultsResponse> getAllOptionsCountForAllQuestions();
    void createAnswer(AnswerRequest answerRequest) throws Exception;
}
