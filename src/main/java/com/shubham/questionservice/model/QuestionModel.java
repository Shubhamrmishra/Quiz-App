package com.shubham.questionservice.model;

import lombok.Data;

@Data
public class QuestionModel {
    public Integer id;
    public String questionTitle;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String rightAnswer;

}
