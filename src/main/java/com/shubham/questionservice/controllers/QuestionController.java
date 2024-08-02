package com.shubham.questionservice.controllers;

import com.shubham.questionservice.model.QuestionModel;
import com.shubham.questionservice.entity.Question;
import com.shubham.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    QuestionService questionService;
    // Constructor injection
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
//    @GetMapping("/allQuestions/count")
//    public ResponseEntity<Integer> getAllQuestionsCount() {
//        return questionService.getQuestionCount();
//    }
    @GetMapping("/allQuestions/{id}")
    public Question getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }
    @GetMapping("/allQuestions/{id}/answer")
    public String getAnswerById(@PathVariable Integer id) {
        return  questionService.getAnswerById(id);
    }
    @GetMapping("/category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }
//    @GetMapping("/category/{category}/count")
//    public Integer getCategoryCount(@PathVariable String category) {
//        return questionService.getCategoryCount(category);
//    }
    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);
        return "Question of this id " + id + " is deleted successfully";
    }



}
