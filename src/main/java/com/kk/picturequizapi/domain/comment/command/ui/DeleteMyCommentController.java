package com.kk.picturequizapi.domain.comment.command.ui;


import com.kk.picturequizapi.domain.comment.command.application.DeleteMyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteMyCommentController {

    private final DeleteMyCommentService deleteMyCommentService;

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteMyComment(@PathVariable("commentId") String commentId){
        deleteMyCommentService.deleteMyComment(commentId);
        return ResponseEntity.ok().build();
    }
}
