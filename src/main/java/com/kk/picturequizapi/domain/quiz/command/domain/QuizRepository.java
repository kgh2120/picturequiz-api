package com.kk.picturequizapi.domain.quiz.command.domain;

import java.util.Optional;
import java.util.UUID;

public interface QuizRepository {

    Quiz save(Quiz quiz);

    Optional<Quiz> findById(QuizId quizId);

    default String nextId() {
        return UUID.randomUUID().toString();
    }

    boolean existsById(QuizId quizId);

    void deleteById(QuizId quizId);

}
