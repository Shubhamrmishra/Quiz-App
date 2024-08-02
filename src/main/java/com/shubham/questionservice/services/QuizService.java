package com.shubham.questionservice.services;

import com.shubham.questionservice.dao.QuestionDao;
import com.shubham.questionservice.dao.QuizDao;
import com.shubham.questionservice.entity.Question;
import com.shubham.questionservice.entity.Quiz;
import com.shubham.questionservice.mapper.QuizMapper;
import com.shubham.questionservice.model.QuizModel;
import com.shubham.questionservice.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public List<Quiz> getQuizzes(String title) {
        List<Quiz> quizList= quizDao.getQuizzesByTitle(title);
        List<Optional<Quiz>> quizList2 = new ArrayList<>();
        List<QuizModel> ql = new ArrayList<>();
        for (int i = 0; i <=quizList.size(); i++) {
            quizList2.add(quizDao.findById(i));
        }
        return quizList;
    }
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> randomQuestion = questionDao.createQuiz(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(randomQuestion);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

//public ResponseEntity<List<Quiz>> createQuiz(String category, int numQ, String title) {
//    List<Question> randomQuestion = questionDao.createQuiz(category, numQ);
//            List<QuestionModel> questionEntities = new ArrayList<QuestionModel>();
//            QuestionMapper mapper = new QuestionMapper();
//            for( Question question : randomQuestion ) {
//                QuestionModel questionModel = mapper.questionMapper(question);
//                questionEntities.add(questionModel);
//            }
//
//    Quiz quiz = new Quiz();
//    quiz.setTitle(title);
//    quiz.setQuestions(randomQuestion);
//    quizDao.save(quiz);
//    List<Quiz> quizzes = getQuizzes(title);
//
//
//    return new ResponseEntity<>(quizzes, HttpStatus.CREATED);
//}





//
//    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {
//        List<Question> randomQuestion = questionDao.createQuiz(category, numQ);
//
//        List<QuizModel> quizModels = new ArrayList<QuizModel>();
//        QuizMapper mapper = new QuizMapper();
//        for ( Question question: randomQuestion){
//            QuizModel q = mapper.createQuiz(question);
//            quizModels.add(q);
//        }
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(quizModels);
//        Quiz save = quizDao.save(quiz);
//        return new ResponseEntity<>(save, HttpStatus.CREATED);
//    }


    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizDao.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    public List<QuizModel> newQuiz(String category, String title) {

//        List<Question> quiz = questionDao.createQuiz(category, numQ);
        List<Question> quiz = questionDao.newQuiz(category);
        List<QuizModel> quizEntities = new ArrayList<QuizModel>();
        QuizMapper mapper = new QuizMapper();
        for( Question question : quiz ) {
            QuizModel quizModel = mapper.createQuiz(question);
            quizEntities.add(quizModel);
        }
        return quizEntities;
    }

    public ResponseEntity<List<QuizModel>> getQuizById(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<QuizModel> quizForUser = new ArrayList<QuizModel>();
        List<Question> question = quiz.get().getQuestions();
        for ( Question question1 : question ) {
            QuizModel quizModel = new QuizModel(question1.getId(),question1.getQuestionTitle(),question1.getOption1(),question1.getOption2(),question1.getOption3(),question1.getOption4());
            quizForUser.add(quizModel);
        }
        return new ResponseEntity<>(quizForUser,HttpStatus.OK);
    }
    public ResponseEntity<Integer> calculateResult(Integer id, List<UserResponse> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        Integer result = 0;
        int i = 0;
        for(UserResponse response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                result++;
            i++;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
