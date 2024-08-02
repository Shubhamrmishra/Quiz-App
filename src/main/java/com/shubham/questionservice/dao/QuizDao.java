package com.shubham.questionservice.dao;

import com.shubham.questionservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
    List<Quiz> getQuizzesByTitle(String title);


}
