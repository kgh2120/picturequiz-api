package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQuizRepository extends QuizRepository, JpaRepository<Quiz, QuizId> {

    List<Quiz> findAllByAuthor_UserId(UserId userId);
}
