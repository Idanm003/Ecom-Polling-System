package PollingSystem.service.questions;

import PollingSystem.model.questions.Questions;
import PollingSystem.repository.answers.AnswersRepo;
import PollingSystem.repository.questions.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private AnswersRepo answersRepo;

    private void validateQuestion(Questions questions) throws Exception {
        if (isBlank(questions.getQuestionTitle())) {
            throw new Exception("Question title is missing");
        }
        if (isBlank(questions.getOption1())
            || isBlank(questions.getOption2())
            || isBlank(questions.getOption3())
            || isBlank(questions.getOption4())
        ) {
            throw new Exception("A question must have 4 answer options");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

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
        validateQuestion(questions);
        return questionsRepo.createQuestion(questions);
    }

    @Override
    public void updateQuestion(Questions questions) throws Exception {
        if (questions.getQuestionId() == null) {
            throw new Exception("Question id is missing");
        }
        if (questionsRepo.getQuestionsById(questions.getQuestionId()) == null) {
            throw new Exception("Question with id " + questions.getQuestionId() + " not found");
        }
        validateQuestion(questions);
        questionsRepo.updateQuestion(questions);
    }

    @Override
    public void deleteQuestionById(Integer id) throws Exception {
        if (questionsRepo.getQuestionsById(id) == null) {
            throw new Exception("Question with id " + id + " not found");
        }
        answersRepo.deleteAllAnswersByQuestionId(id);
        questionsRepo.deleteQuestion(id);
    }

}
