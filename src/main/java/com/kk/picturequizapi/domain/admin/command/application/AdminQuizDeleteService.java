package com.kk.picturequizapi.domain.admin.command.application;


import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminQuizDeleteService {

    private final QuizRepository quizRepository;

    public void deleteQuiz(String quizId){

        quizRepository.deleteById(QuizId.of(quizId));

    }

}
