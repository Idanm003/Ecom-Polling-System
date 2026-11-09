package PollingSystem.controller.answer;

import PollingSystem.model.answer.AnswerRequest;
import PollingSystem.model.answer.UserAnswersResponse;
import PollingSystem.model.question.QuestionResultsResponse;
import PollingSystem.service.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(value = "/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public String submitAnswer(@RequestBody AnswerRequest answerRequest) throws Exception {
        answerService.createAnswer(answerRequest);
        return "Answer of user " + answerRequest.getUserId() +
                " to question " + answerRequest.getQuestionId() +
                " saved successfully";
    }

    @GetMapping(value = "/question/{questionId}/options_count")
    public QuestionResultsResponse getOptionsCountByQuestionId(@PathVariable(value = "questionId") Integer questionId) {
        return answerService.getOptionsCountByQuestionId(questionId);
    }

    @GetMapping(value = "/question/{questionId}/count")
    public Integer getAnswersCountByQuestionId(@PathVariable(value = "questionId") Integer questionId) {
        return answerService.getAnswersCountByQuestionId(questionId);
    }

    @GetMapping(value = "/user/{userId}")
    public UserAnswersResponse getAllAnswersByUserId(@PathVariable(value = "userId") Integer userId) {
        return answerService.getAllAnswersByUserId(userId);
    }

    @GetMapping(value = "/user/{userId}/count")
    public Integer getAnswersCountByUserId(@PathVariable(value = "userId") Integer userId) {
        return answerService.getAnswersCountByUserId(userId);
    }

    @GetMapping(value = "/all/options_count")
    public List<QuestionResultsResponse> getAllOptionsCountForAllQuestions() {
        return answerService.getAllOptionsCountForAllQuestions();
    }
}