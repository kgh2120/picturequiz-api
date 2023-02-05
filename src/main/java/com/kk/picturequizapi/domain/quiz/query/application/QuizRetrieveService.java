package com.kk.picturequizapi.domain.quiz.query.application;


import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizRetrieveService {

    private final QuizSearchDao quizSearchDao;


    @Cacheable(cacheNames = "quiz_info", key = "#quizId")
    public QuizSearch retrieveQuiz(String quizId) {
        return quizSearchDao.retrieveQuiz(QuizId.of(quizId));
    }
}
