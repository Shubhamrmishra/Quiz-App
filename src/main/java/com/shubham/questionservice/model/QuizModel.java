package com.shubham.questionservice.model;


public class QuizModel {


        public Integer id;
        public String questionTitle;
        public String option1;
        public String option2;
        public String option3;
        public String option4;

        public QuizModel(Integer id, String questionTitle, String option1, String option2, String option3, String option4) {
                this.id = id;
                this.questionTitle = questionTitle;
                this.option1 = option1;
                this.option2 = option2;
                this.option3 = option3;
                this.option4 = option4;
        }

        public QuizModel() {

        }
}
