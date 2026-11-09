package PollingSystem.service.question;

import PollingSystem.model.question.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(Integer id);
    List<Question> getAllQuestions();
    Integer createQuestion(Question question) throws Exception;
    void updateQuestion(Question question) throws Exception;
    void deleteQuestionById(Integer id) throws Exception;
}
