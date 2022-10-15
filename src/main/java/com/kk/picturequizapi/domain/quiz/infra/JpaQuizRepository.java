package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizData;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQuizRepository extends QuizRepository, JpaRepository<QuizData, QuizId> {


}
