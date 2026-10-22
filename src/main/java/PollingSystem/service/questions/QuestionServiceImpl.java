package PollingSystem.service.questions;

import PollingSystem.model.questions.Questions;
import PollingSystem.repository.questions.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepo questionsRepo;

    @Override
    public Questions getQuestionById(Integer id) {
        return questionsRepo.getQuestionsById(id);
    }

    @Override
    public List<Questions> getAllQuestions() {
        return questionsRepo.getAllQuestions();
    }

    @Override
    public Integer createQuestion(Questions questions) throws Exception {
        return questionsRepo.createQuestion(questions);
    }

    @Override
    public void updateQuestion(Questions questions) throws Exception {
        if (questionsRepo.getQuestionsById(questions.getQuestionId()) == null) {
            throw new Exception("Questions with id " + questions.getQuestionId() + " not found");
        }
        questionsRepo.updateQuestion(questions);
    }

    @Override
    public void deleteQuestionById(Integer id) throws Exception {
        questionsRepo.deleteQuestion(id);
    }

}
