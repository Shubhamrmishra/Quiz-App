package com.shubham.questionservice.controllers;

import com.shubham.questionservice.entity.Quiz;
import com.shubham.questionservice.model.QuizModel;
import com.shubham.questionservice.model.UserResponse;
import com.shubham.questionservice.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @PostMapping("/test")
    public List<Quiz> getQuizzes(@RequestParam String title){
        return quizService.getQuizzes(title);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuizModel>> getQuizById(@PathVariable Integer id){
        return quizService.getQuizById(id);
    }

    @GetMapping("/allQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @GetMapping("/new")
    public List<QuizModel> newQuiz(@RequestParam String category, @RequestParam String title){
        return quizService.newQuiz(category,title);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id, @RequestBody List<UserResponse> responses){
        return quizService.calculateResult(id, responses);
    }
}
