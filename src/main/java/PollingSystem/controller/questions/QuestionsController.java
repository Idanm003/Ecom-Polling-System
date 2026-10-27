package PollingSystem.controller.questions;

import PollingSystem.model.questions.Questions;
import PollingSystem.service.questions.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/questions")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/{id}")
    public Object getQuestionById(@PathVariable(value = "id") Integer id) {
        Questions question = questionService.getQuestionById(id);
        if (question == null) {
            return "Error: Question with id " + id + " not found";
        }
        return question;
    }

    @GetMapping(value = "/all")
    public List<Questions> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping(value = "/create")
    public Object createQuestion(@RequestBody Questions questions) {
        try {
            return questionService.createQuestion(questions);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping(value = "/update")
    public String updateQuestion(@RequestBody Questions questions) {
        try {
            questionService.updateQuestion(questions);
            return "Question with id " + questions.getQuestionId() + " updated successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteQuestionById(@PathVariable(value = "id") Integer id) {
        try {
            questionService.deleteQuestionById(id);
            return "Question with id " + id + " and all of its answers deleted successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}