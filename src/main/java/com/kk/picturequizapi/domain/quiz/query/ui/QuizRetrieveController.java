package com.kk.picturequizapi.domain.quiz.query.ui;


import com.kk.picturequizapi.domain.quiz.query.application.QuizRetrieveService;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizRetrieveController {

    private final QuizRetrieveService quizRetrieveService;

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<QuizSearch> retrieveQuiz(@PathVariable("quizId") String quizId) {
        QuizSearch dto = quizRetrieveService.retrieveQuiz(quizId);
        return ResponseEntity.ok(dto);
    }

}
