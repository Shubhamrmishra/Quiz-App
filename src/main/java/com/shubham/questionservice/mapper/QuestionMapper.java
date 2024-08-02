package com.shubham.questionservice.mapper;

import com.shubham.questionservice.model.QuestionModel;
import com.shubham.questionservice.entity.Question;

public class QuestionMapper {

    public QuestionModel questionMapper(Question question){
        QuestionModel questionModel = new QuestionModel();
        questionModel.id = question.getId();
        questionModel.questionTitle = question.getQuestionTitle();
        questionModel.option1 = question.getOption1();
        questionModel.option2  = question.getOption2();
        questionModel.option3  = question.getOption3();
        questionModel.option4  = question.getOption4();

        return questionModel;
    }

    public QuestionModel getAnswer(Question question) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.questionTitle = question.getQuestionTitle();
        questionModel.rightAnswer = question.getRightAnswer();

        return questionModel;
    }

}
