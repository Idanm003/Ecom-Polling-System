package PollingSystem.controller.answers;

import PollingSystem.requests.answers.AnswerRequest;
import PollingSystem.responses.questions.QuestionResultsResponse;
import PollingSystem.service.answers.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/answers")
public class AnswersController {

    @Autowired
    private AnswersService answersService;

    @PostMapping(value = "/submit")
    public String submitAnswer(@RequestBody AnswerRequest answerRequest) {
        try {
            answersService.createAnswer(answerRequest);
            return "Answer of user " + answerRequest.getUserId() +
                    " to question " + answerRequest.getQuestionId() +
                    " saved successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(value = "/question/{questionId}/options_count")
    public Object getOptionsCountByQuestionId(@PathVariable(value = "questionId") Integer questionId) {
        try {
            return answersService.getOptionsCountByQuestionId(questionId);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(value = "/question/{questionId}/count")
    public Object getAnswersCountByQuestionId(@PathVariable(value = "questionId") Integer questionId) {
        try {
            return answersService.getAnswersCountByQuestionId(questionId);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(value = "/user/{userId}")
    public Object getAllAnswersByUserId(@PathVariable(value = "userId") Integer userId) {
        try {
            return answersService.getAllAnswersByUserId(userId);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(value = "/user/{userId}/count")
    public Object getAnswersCountByUserId(@PathVariable(value = "userId") Integer userId) {
        try {
            return answersService.getAnswersCountByUserId(userId);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(value = "/all/options_count")
    public List<QuestionResultsResponse> getAllOptionsCountForAllQuestions() {
        return answersService.getAllOptionsCountForAllQuestions();
    }
}