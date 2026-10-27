package PollingSystem.service.answers;

import PollingSystem.model.answers.Answers;
import PollingSystem.model.questions.Questions;
import PollingSystem.model.user.User;
import PollingSystem.repository.answers.AnswersRepo;
import PollingSystem.requests.answers.AnswerRequest;
import PollingSystem.responses.questions.QuestionResultsResponse;
import PollingSystem.responses.answers.AnswerDetailsResponse;
import PollingSystem.responses.answers.UserAnswersResponse;
import PollingSystem.service.questions.QuestionService;
import PollingSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private QuestionService questionService;

    private QuestionResultsResponse countOptions(Questions question) {
        Integer questionId = question.getQuestionId();
        Map<String, Integer> optionsCount = new LinkedHashMap<>();

        for (int option = 1; option <= 4; option++) {
            optionsCount.put(question.getOptionText(option),
                    answersRepo.countAnswersByQuestionIdAndOptions(
                            questionId,
                            option
                    )
            );
        }

        return new QuestionResultsResponse(
                questionId,
                question.getQuestionTitle(),
                optionsCount,
                answersRepo.countAllAnswersByQuestionId(questionId)
        );
    }

    @Override
    public QuestionResultsResponse getOptionsCountByQuestionId(Integer questionId) throws Exception {
        Questions question = questionService.getQuestionById(questionId);
        if (question == null) {
            throw new Exception("Question with id " + questionId + " not found");
        }
        return countOptions(question);
    }

    @Override
    public Integer getAnswersCountByQuestionId(Integer questionId) throws Exception {
        if (questionService.getQuestionById(questionId) == null) {
            throw new Exception("Question with id " + questionId + " not found");
        }
        return answersRepo.countAllAnswersByQuestionId(questionId);
    }

    @Override
    public Integer getAnswersCountByUserId(Integer userId) throws Exception {
        if (userService.getUserById(userId) == null) {
            throw new Exception("User with id " + userId + " not found");
        }
        return answersRepo.countAllAnswersByUserId(userId);
    }

    @Override
    public UserAnswersResponse getAllAnswersByUserId(Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new Exception("User with id " + userId + " not found");
        }
        List<AnswerDetailsResponse> userAnswers = new ArrayList<>();
        for (Answers answer : answersRepo.getAllAnswersByUserId(userId)) {
            Questions question = questionService.getQuestionById(answer.getQuestionId());
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
        for (Questions question : questionService.getAllQuestions()) {
            allOptionsCount.add(countOptions(question));
        }
        return allOptionsCount;
    }

    @Override
    public void createAnswer(AnswerRequest answerRequest) throws Exception {
        Answers answers = answerRequest.toAnswers();

        if (answers.getUserId() == null) {
            throw new Exception("User id is missing");
        }
        if (userService.getUserById(answers.getUserId()) == null) {
            throw new Exception("User with id " + answers.getUserId() + " not found - only registered users can submit answers");
        }
        if (answers.getQuestionId() == null) {
            throw new Exception("Question id is missing");
        }
        if (questionService.getQuestionById(answers.getQuestionId()) == null) {
            throw new Exception("Question with id " + answers.getQuestionId() + " not found");
        }
        if (answers.getSelectedOption() == null
                || answers.getSelectedOption() < 1
                || answers.getSelectedOption() > 4) {
            throw new Exception("Selected option must be between 1 and 4");
        }
        if (answersRepo.getAnswersByUserIdAndQuestionId(answers.getUserId(), answers.getQuestionId()) != null) {
            throw new Exception("User with id " + answers.getUserId() + " already answered question with id " + answers.getQuestionId());
        }
        answersRepo.createAnswers(answers);
    }
}
