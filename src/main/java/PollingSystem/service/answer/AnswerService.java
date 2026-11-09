package PollingSystem.service.answer;

import PollingSystem.model.answer.AnswerRequest;
import PollingSystem.model.answer.UserAnswersResponse;
import PollingSystem.model.question.QuestionResultsResponse;

import java.util.List;

public interface AnswerService {
    QuestionResultsResponse getOptionsCountByQuestionId(Integer questionId);
    Integer getAnswersCountByQuestionId(Integer questionId);
    Integer getAnswersCountByUserId(Integer userId);
    UserAnswersResponse getAllAnswersByUserId(Integer userId);
    List<QuestionResultsResponse> getAllOptionsCountForAllQuestions();
    void createAnswer(AnswerRequest answerRequest) throws Exception;
}