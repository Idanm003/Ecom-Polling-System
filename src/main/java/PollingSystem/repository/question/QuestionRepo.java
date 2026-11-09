package PollingSystem.repository.question;

import PollingSystem.model.question.Question;

import java.util.List;

public interface QuestionRepo {
    Question getQuestionById(Integer id);
    List<Question> getAllQuestions();
    Integer createQuestion(Question question) throws Exception;
    void updateQuestion(Question question) throws Exception;
    void deleteQuestion(Integer id) throws Exception;
}
