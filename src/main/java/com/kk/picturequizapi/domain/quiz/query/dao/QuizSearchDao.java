package com.kk.picturequizapi.domain.quiz.query.dao;

import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import com.kk.picturequizapi.domain.users.entity.UserId;

public interface QuizSearchDao {

    QuizSearchResponse searchQuizByCondition(QuizSearchCondition condition,  int pageNum);

    QuizSearchResponse searchMyQuizzes(UserId userId, int pageNum);
}
