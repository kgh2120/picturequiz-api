package com.kk.picturequizapi.domain.quiz.query.ui;

import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizSearchController {

    private final QuizSearchDao quizSearchDao;

    @PostMapping("/quiz")
    public ResponseEntity<QuizSearchResponse> searchQuizzes(@RequestBody QuizSearchCondition cond) {
        QuizSearchResponse response = quizSearchDao.searchQuizByCondition(cond, cond.getPageNum());
        return ResponseEntity.ok(response);
    }
}
