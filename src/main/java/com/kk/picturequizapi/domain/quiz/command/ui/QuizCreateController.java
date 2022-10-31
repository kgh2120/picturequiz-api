package com.kk.picturequizapi.domain.quiz.command.ui;

import com.kk.picturequizapi.domain.quiz.command.application.QuizCreateRequest;
import com.kk.picturequizapi.domain.quiz.command.application.QuizCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class QuizCreateController {
    private final QuizCreateService quizCreateService;

    @PostMapping("/quiz/add")
    public ResponseEntity<Void> createQuiz(@RequestPart("image")MultipartFile file,
                                           @RequestPart("quiz")QuizCreateRequest request) {
        quizCreateService.createQuiz(file,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
