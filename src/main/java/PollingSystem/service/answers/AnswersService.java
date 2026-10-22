package PollingSystem.service.answers;

import PollingSystem.model.answers.Answers;

import java.util.List;
import java.util.Map;

public interface AnswersService {
    Map<String, Integer> getOptionsCountByQuestionId(Integer questionId) throws Exception;
    Integer getAnswersCountByQuestionId(Integer questionId) throws Exception;
    Integer getAnswersCountByUserId(Integer UserId) throws Exception;
    List<Answers> getAllAnswersByUserId(Integer UserId) throws Exception;
    Map<Integer, Map<String, Integer>> getAllOptionsCountForAllQuestions() throws Exception;
    void createAnswer(Answers answers) throws Exception;
}
