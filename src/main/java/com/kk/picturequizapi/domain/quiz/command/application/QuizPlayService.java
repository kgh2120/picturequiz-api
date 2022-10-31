package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizData;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import com.kk.picturequizapi.domain.quiz.exception.QuizNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class QuizPlayService {

    private final QuizRepository quizRepository;

    public PlayQuizResponse playQuiz(String quizId) {
        QuizData quiz = quizRepository.findById(QuizId.of(quizId))
                .orElseThrow(QuizNotFoundByIdException::new);
        String url = quiz.playQuiz();

        return new PlayQuizResponse(url);

    }
}
