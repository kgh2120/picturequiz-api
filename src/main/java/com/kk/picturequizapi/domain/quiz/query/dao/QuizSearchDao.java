package com.kk.picturequizapi.domain.quiz.query.dao;

import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;

public interface QuizSearchDao {

    QuizSearchResponse searchQuizByCondition(QuizSearchCondition condition,  int pageNum);
}
