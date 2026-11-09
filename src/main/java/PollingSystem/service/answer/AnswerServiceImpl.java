package PollingSystem.service.answer;

import PollingSystem.model.answer.Answer;
import PollingSystem.model.answer.AnswerDetailsResponse;
import PollingSystem.model.answer.AnswerOption;
import PollingSystem.model.answer.AnswerRequest;
import PollingSystem.model.answer.UserAnswersResponse;
import PollingSystem.model.question.Question;
import PollingSystem.model.question.QuestionResultsResponse;
import PollingSystem.model.user.User;
import PollingSystem.repository.answer.AnswerRepo;
import PollingSystem.service.question.QuestionService;
import PollingSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    private QuestionResultsResponse countOptions(Question question) {
        Integer questionId = question.getQuestionId();
        Map<String, Integer> optionsCount = new LinkedHashMap<>();

        for (AnswerOption option : AnswerOption.values()) {
            optionsCount.put(
                    question.getOptionText(option.getValue()),
                    answerRepo.countAnswersByQuestionIdAndOptions(questionId, option.getValue())
            );
        }

        return new QuestionResultsResponse(
                questionId,
                question.getQuestionTitle(),
                optionsCount,
                answerRepo.countAllAnswersByQuestionId(questionId)
        );
    }

    @Override
    public QuestionResultsResponse getOptionsCountByQuestionId(Integer questionId) {
        Question question = questionService.getQuestionById(questionId); // throws 404 if missing
        return countOptions(question);
    }

    @Override
    public Integer getAnswersCountByQuestionId(Integer questionId) {
        questionService.getQuestionById(questionId); // throws 404 if missing
        return answerRepo.countAllAnswersByQuestionId(questionId);
    }

    @Override
    public Integer getAnswersCountByUserId(Integer userId) {
        userService.getUserById(userId); // throws 404 if missing
        return answerRepo.countAllAnswersByUserId(userId);
    }

    @Override
    public UserAnswersResponse getAllAnswersByUserId(Integer userId) {
        User user = userService.getUserById(userId); // throws 404 if missing

        List<AnswerDetailsResponse> userAnswers = new ArrayList<>();
        for (Answer answer : answerRepo.getAllAnswersByUserId(userId)) {
            Question question = questionService.getQuestionById(answer.getQuestionId());
            userAnswers.add(new AnswerDetailsResponse(
                    question.getQuestionId(),
                    question.getQuestionTitle(),
                    answer.getSelectedOption(),
                    question.getOptionText(answer.getSelectedOption())
            ));
        }
        return new UserAnswersResponse(user, userAnswers);
    }

    @Override
    public List<QuestionResultsResponse> getAllOptionsCountForAllQuestions() {
        List<QuestionResultsResponse> allOptionsCount = new ArrayList<>();
        for (Question question : questionService.getAllQuestions()) {
            allOptionsCount.add(countOptions(question));
        }
        return allOptionsCount;
    }

    @Override
    public void createAnswer(AnswerRequest answerRequest) throws Exception {
        Answer answer = answerRequest.toAnswer();

        if (answer.getUserId() == null) {
            throw new Exception("User id is missing");
        }
        if (answer.getQuestionId() == null) {
            throw new Exception("Question id is missing");
        }

        userService.getUserById(answer.getUserId());         // throws 404 if not registered
        questionService.getQuestionById(answer.getQuestionId()); // throws 404 if missing

        if (!AnswerOption.isValid(answer.getSelectedOption())) {
            throw new Exception("Selected option must be between 1 and " + AnswerOption.values().length);
        }
        if (answerRepo.getAnswerByUserIdAndQuestionId(answer.getUserId(), answer.getQuestionId()) != null) {
            throw new Exception("User with id " + answer.getUserId() +
                    " already answered question with id " + answer.getQuestionId());
        }
        answerRepo.createAnswer(answer);
    }
}