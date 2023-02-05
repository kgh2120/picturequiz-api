package com.kk.picturequizapi.domain.quiz.command.ui;

import com.kk.picturequizapi.domain.quiz.command.application.QuizDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizDeleteController {

    private final QuizDeleteService quizDeleteService;

    @DeleteMapping("/quiz/{quizId}")
    public ResponseEntity<Void> deleteMyQuiz(@PathVariable("quizId") String quizId){
        quizDeleteService.deleteQuiz(quizId);
        return ResponseEntity.ok().build();
    }

}
