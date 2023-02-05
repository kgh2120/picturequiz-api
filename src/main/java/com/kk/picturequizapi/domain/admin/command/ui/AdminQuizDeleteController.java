package com.kk.picturequizapi.domain.admin.command.ui;

import com.kk.picturequizapi.domain.admin.command.application.AdminQuizDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminQuizDeleteController {

    private final AdminQuizDeleteService adminQuizDeleteService;

    @DeleteMapping("/admin/quiz/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("quizId") String quizId){

        adminQuizDeleteService.deleteQuiz(quizId);
        return ResponseEntity.ok().build();
    }

}
