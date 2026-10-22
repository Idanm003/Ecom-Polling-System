package PollingSystem.repository.questions;

import PollingSystem.model.questions.Questions;

import java.util.List;

public interface QuestionsRepo {
    Questions getQuestionsById(Integer id);
    List<Questions> getAllQuestions();
    Integer createQuestion(Questions questions) throws Exception;
    void updateQuestion(Questions questions) throws Exception;
    void deleteQuestion(Integer id) throws Exception;
}
