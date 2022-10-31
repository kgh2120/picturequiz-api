package com.kk.picturequizapi.domain.quiz.command.domain;

import java.util.Optional;
import java.util.UUID;

public interface QuizRepository {

    QuizData save(QuizData quizData);

    Optional<QuizData> findById(QuizId quizId);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
