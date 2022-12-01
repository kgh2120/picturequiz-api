package com.kk.picturequizapi.domain.quiz.query.ui;

import com.kk.picturequizapi.domain.quiz.query.application.MyQuizService;
import com.kk.picturequizapi.domain.quiz.query.application.QuizSearchService;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuizSearchController {

    private final QuizSearchDao quizSearchDao;
    private final MyQuizService myQuizService;
    private final QuizSearchService quizSearchService;

    @PostMapping("/quiz")
    public ResponseEntity<QuizSearchResponse> searchQuizzes(@RequestBody QuizSearchCondition cond) {
        QuizSearchResponse response = quizSearchService.findQuizzes(cond);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/quiz/my")
    public ResponseEntity<QuizSearchResponse> searchMyQuizzes(@RequestParam("pageNum") Integer pageNum) {
        QuizSearchResponse response = myQuizService.findMyQuizzes(pageNum);
        return ResponseEntity.ok(response);
    }

}
