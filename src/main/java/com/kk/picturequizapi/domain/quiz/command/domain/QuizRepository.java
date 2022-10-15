package com.kk.picturequizapi.domain.quiz.command.domain;

import java.util.UUID;

public interface QuizRepository {

    QuizData save(QuizData quizData);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
