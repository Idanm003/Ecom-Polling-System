package PollingSystem.repository.answer;

import PollingSystem.model.answer.Answer;

import java.util.List;

public interface AnswerRepo {
    Answer getAnswerByUserIdAndQuestionId(Integer userId, Integer questionId);
    List<Answer> getAllAnswersByUserId(Integer userId);
    Integer countAllAnswersByUserId(Integer userId);
    Integer countAllAnswersByQuestionId(Integer questionId);
    Integer countAnswersByQuestionIdAndOptions(Integer questionId, Integer selectedOption);
    void createAnswer(Answer answer) throws Exception;
    void deleteAllAnswersByUserId(Integer userId) throws Exception;
    void deleteAllAnswersByQuestionId(Integer questionId) throws Exception;
}
