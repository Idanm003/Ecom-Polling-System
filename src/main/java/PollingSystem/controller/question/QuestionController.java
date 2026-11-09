package PollingSystem.controller.question;

import PollingSystem.model.question.Question;
import PollingSystem.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Integer id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping(value = "/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createQuestion(@RequestBody Question question) throws Exception {
        return questionService.createQuestion(question);
    }

    @PutMapping(value = "/update")
    public String updateQuestion(@RequestBody Question question) throws Exception {
        questionService.updateQuestion(question);
        return "Question with id " + question.getQuestionId() + " updated successfully";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteQuestionById(@PathVariable(value = "id") Integer id) throws Exception {
        questionService.deleteQuestionById(id);
        return "Question with id " + id + " and all of its answers deleted successfully";
    }
}