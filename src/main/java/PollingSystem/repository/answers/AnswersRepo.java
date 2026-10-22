package PollingSystem.repository.answers;

import PollingSystem.model.answers.Answers;

import java.util.List;

public interface AnswersRepo {
    Answers getAnswersByUserIdAndQuestionId(Integer userId, Integer questionId);
    List<Answers> getAllAnswersByUserId(Integer userId);
    Integer countAllAnswersByUserId(Integer userId);
    Integer countAnswersByQuestionIdAndOptions(Integer questionId, Integer optionId);
    void createAnswers(Answers answers) throws Exception;
}
