package PollingSystem.service.question;

import PollingSystem.exceptions.NotFoundException;
import PollingSystem.model.answer.AnswerOption;
import PollingSystem.model.question.Question;
import PollingSystem.repository.answer.AnswerRepo;
import PollingSystem.repository.question.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private AnswerRepo answerRepo;

    private void validateQuestion(Question question) throws Exception {
        if (isBlank(question.getQuestionTitle())) {
            throw new Exception("Question title is missing");
        }
        int requiredOptions = AnswerOption.values().length;
        if (isBlank(question.getOption1())
            || isBlank(question.getOption2())
            || isBlank(question.getOption3())
            || isBlank(question.getOption4())
        ) {
            throw new Exception("A question must have " + requiredOptions + " answer options");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    @Override
    public Question getQuestionById(Integer id) {
        Question question = questionRepo.getQuestionById(id);
        if (question == null) {
            throw new NotFoundException("Question with id " + id + " not found");
        }
        return question;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.getAllQuestions();
    }

    @Override
    public Integer createQuestion(Question question) throws Exception {
        validateQuestion(question);
        return questionRepo.createQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) throws Exception {
        if (question.getQuestionId() == null) {
            throw new Exception("Question id is missing");
        }
        getQuestionById(question.getQuestionId());
        validateQuestion(question);
        questionRepo.updateQuestion(question);
    }

    @Override
    public void deleteQuestionById(Integer id) throws Exception {
        getQuestionById(id);
        answerRepo.deleteAllAnswersByQuestionId(id);
        questionRepo.deleteQuestion(id);
    }
}