package PollingSystem.repository.answers;

import PollingSystem.model.answers.Answers;

import java.util.List;

public interface AnswersRepo {
    Answers getAnswersByUserIdAndQuestionId(Integer userId, Integer questionId);
    List<Answers> getAllAnswersByUserId(Integer userId);
    Integer countAllAnswersByUserId(Integer userId);
    Integer countAllAnswersByQuestionId(Integer questionId);
    Integer countAnswersByQuestionIdAndOptions(Integer questionId, Integer selectedOption);
    void createAnswers(Answers answers) throws Exception;
    void deleteAllAnswersByUserId(Integer userId) throws Exception;
    void deleteAllAnswersByQuestionId(Integer questionId) throws Exception;
}
