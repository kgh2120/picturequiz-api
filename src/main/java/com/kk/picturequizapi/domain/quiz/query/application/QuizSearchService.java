package com.kk.picturequizapi.domain.quiz.query.application;

import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class QuizSearchService {

    private final QuizSearchDao quizSearchDao;

    @Cacheable(cacheNames = "quiz", key = "#cond?.answerName + #cond?.tagNames+  #cond.orderCondition + '_'+ #cond.pageNum ")
    public QuizSearchResponse findQuizzes(QuizSearchCondition cond){

        return quizSearchDao.searchQuizByCondition(cond, cond.getPageNum());
    }
}
