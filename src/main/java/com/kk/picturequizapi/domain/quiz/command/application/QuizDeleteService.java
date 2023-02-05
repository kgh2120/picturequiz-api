package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import com.kk.picturequizapi.domain.quiz.exception.QuizIsNotYoursException;
import com.kk.picturequizapi.domain.quiz.exception.QuizNotFoundByIdException;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class QuizDeleteService {

    private final QuizRepository quizRepository;

    public void deleteQuiz(String quizId) {

        validateQuizDeleteAuthority(quizId);

        deleteQuizData(quizId);

    }

    private void deleteQuizData(String quizId) {
        quizRepository.deleteById(QuizId.of(quizId));

    }

    private void validateQuizDeleteAuthority(String quizId) {
        Quiz quiz = quizRepository.findById(QuizId.of(quizId))
                .orElseThrow(QuizNotFoundByIdException::new);
        isYourQuiz(quiz);
    }

    private void isYourQuiz(Quiz quiz) {
        if(!quiz.getAuthor().getUserId().equals(UserId.of(getUserId())))
            throw new QuizIsNotYoursException();
    }

    private Long getUserId() {
        return ((Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getId();
    }
}
