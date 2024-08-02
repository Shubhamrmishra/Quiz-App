package com.shubham.questionservice.services;

import com.shubham.questionservice.dao.QuestionDao;
import com.shubham.questionservice.model.QuestionModel;
import com.shubham.questionservice.exception.QuestionNotFoundException;
import com.shubham.questionservice.mapper.QuestionMapper;
import com.shubham.questionservice.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<QuestionModel>> getAllQuestions() {

        List<Question> questions = questionDao.findAll();
        List<QuestionModel> questionEntities = new ArrayList<QuestionModel>();
        QuestionMapper mapper = new QuestionMapper();
        for( Question question : questions ) {
            QuestionModel questionModel = mapper.questionMapper(question);
            questionEntities.add(questionModel);
        }
        return new ResponseEntity<>(questionEntities, HttpStatus.OK);

    }

    public ResponseEntity<Integer> getQuestionCount() {
        try {
            return new ResponseEntity<>(questionDao.getQuestionCount(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
    }

    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElseThrow(()-> new QuestionNotFoundException("Question for this" + id +"not found"));
    }
    public String getAnswerById(Integer id) {
        QuestionMapper mapper = new QuestionMapper();
        Question question = questionDao.findById(id).orElseThrow(()-> new QuestionNotFoundException("Question for this" + id +"not found"));
        QuestionModel model = mapper.getAnswer(question);

        String answer = model.getRightAnswer();
        String questionTitle = model.getQuestionTitle();
        return questionTitle +"\t ** Right Answer is **\t : " + answer;
    }
    public List<Question> getQuestionByCategory(String category) {
        return questionDao.getQuestionByCategory(category);
    }
    public Integer getCategoryCount(String category) {
      return questionDao.getCategoryCount(category);
    }
    public Question addQuestion(Question question) {

        return questionDao.save(question);
    }

    public String deleteQuestion(Integer id) {

        Question question = questionDao.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with this : " + id + " id"));
        if(question == null) {
            return new QuestionNotFoundException().getMessage();
        }else {
            questionDao.deleteById(id);
            return "Question deleted with this : " + id + " id";
        }
    }


}
