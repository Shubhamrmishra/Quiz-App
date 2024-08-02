package com.shubham.questionservice.dao;

import com.shubham.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> getQuestionByCategory(String category);

//    @Query("SELECT COUNT(q) FROM Questions q WHERE q.category = :category")
//    Integer getCategoryCount(@Param("category") String category);

//    @Query("SELECT COUNT(q) FROM Questions q")
//    Integer getQuestionCount();

    @Query(value = "SELECT * FROM Questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> createQuiz(@Param("category") String category, @Param("numQ") int numQ);

    @Query(value = "SELECT * FROM Questions q WHERE q.category = :category ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Question> newQuiz(@Param("category") String category);

        
}
