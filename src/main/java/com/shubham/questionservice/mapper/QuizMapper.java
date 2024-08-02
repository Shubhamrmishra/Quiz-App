package com.shubham.questionservice.mapper;

import com.shubham.questionservice.entity.Question;
import com.shubham.questionservice.model.QuizModel;

public class QuizMapper {

    public QuizModel createQuiz(Question question){
        QuizModel quizModel = new QuizModel();


        quizModel.id = question.getId();
        quizModel.questionTitle = question.getQuestionTitle();
        quizModel.option1 = question.getOption1();
        quizModel.option2  = question.getOption2();
        quizModel.option3  = question.getOption3();
        quizModel.option4  = question.getOption4();

        return quizModel;
    }

}
