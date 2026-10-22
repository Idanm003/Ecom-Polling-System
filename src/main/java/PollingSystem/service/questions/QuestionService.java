package PollingSystem.service.questions;

import PollingSystem.model.questions.Questions;

import java.util.List;

public interface QuestionService {
    Questions getQuestionById(Integer id);
    List<Questions> getAllQuestions();
    Integer createQuestion(Questions questions) throws Exception;
    void updateQuestion(Questions questions) throws Exception;
    void deleteQuestionById(Integer id) throws Exception;
}
