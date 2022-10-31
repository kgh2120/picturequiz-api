package com.kk.picturequizapi.domain.quiz.command.ui;


import com.kk.picturequizapi.domain.quiz.command.application.PlayQuizResponse;
import com.kk.picturequizapi.domain.quiz.command.application.QuizPlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizPlayController {

    private final QuizPlayService playService;

    @PostMapping("/quiz/{id}")
    public ResponseEntity<PlayQuizResponse> playQuiz(@PathVariable("id") String quizId) {
        PlayQuizResponse response = playService.playQuiz(quizId);
        return ResponseEntity.ok(response);
    }
}
