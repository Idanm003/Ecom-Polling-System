package PollingSystem.service.answers;

import PollingSystem.model.answers.Answers;
import PollingSystem.model.questions.Questions;
import PollingSystem.repository.answers.AnswersRepo;
import PollingSystem.service.questions.QuestionService;
import PollingSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswersRepo answersRepo;

    @Autowired
    private UserService userService;

    @Autowired
    QuestionService questionService;

    private Map<String, Integer> countOptions(Questions questions) {
        Integer questionId = questions.getQuestionId();
        Map<String, Integer> optionsCount = new LinkedHashMap<>();

        optionsCount.put(questions.getOption1(), answersRepo.countAnswersByQuestionIdAndOptions(questionId, 1));
        optionsCount.put(questions.getOption2(), answersRepo.countAnswersByQuestionIdAndOptions(questionId, 2));
        optionsCount.put(questions.getOption3(), answersRepo.countAnswersByQuestionIdAndOptions(questionId, 3));
        optionsCount.put(questions.getOption4(), answersRepo.countAnswersByQuestionIdAndOptions(questionId, 4));

        return optionsCount;
    }

    @Override
    public Map<String, Integer> getOptionsCountByQuestionId(Integer questionId) throws Exception {
        Questions question = questionService.getQuestionById(questionId);
        if (question == null) {
            throw new Exception("Question Not Found");
        }
        return countOptions(question);
    }

    @Override
    public Integer getAnswersCountByQuestionId(Integer questionId) throws Exception {
        if (questionService.getQuestionById(questionId) == null) {
            throw new Exception("Question Not Found");
        }
        return answersRepo.countAllQuestionsByUserId(questionId);
    }

    @Override
    public Integer getAnswersCountByUserId(Integer UserId) throws Exception {
        if (userService.getUserById(UserId) == null) {
            throw new Exception("User Not Found");
        }
        return answersRepo.countAllAnswersByUserId(UserId);
    }

    @Override
    public List<Answers> getAllAnswersByUserId(Integer UserId) throws Exception {
        if (userService.getUserById(UserId) == null) {
            throw new Exception("User Not Found");
        }
        return answersRepo.getAllAnswersByUserId(UserId);
    }

    @Override
    public Map<Integer, Map<String, Integer>> getAllOptionsCountForAllQuestions() throws Exception {
        Map<Integer, Map<String, Integer>> allOptionsCount = new LinkedHashMap<>();
        for (Questions question : questionService.getAllQuestions()) {
            allOptionsCount.put(question.getQuestionId(), countOptions(question));
        }
        return allOptionsCount;
    }

    @Override
    public void createAnswer(Answers answers) throws Exception {
        if (userService.getUserById(answers.getUserId()) == null) {
            throw new Exception("User Not Found");
        }
        if (questionService.getQuestionById(answers.getQuestionId()) == null) {
            throw new Exception("Question Not Found");
        }
        if (answers.getSelectedOption() == null
        || answers.getSelectedOption() < 1
        || answers.getSelectedOption() > 4) {
            throw new Exception("Selected Option Not Found Must be between 1 and 4");
        }
        if (answersRepo.getAnswersByUserIdAndQuestionId(answers.getUserId(), answers.getQuestionId()) == null) {
            throw new Exception("User " + answers.getUserId() + " already answered question " + answers.getQuestionId());
        }
    }
}
