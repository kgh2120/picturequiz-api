package com.kk.picturequizapi.domain.admin.command.ui;

import com.kk.picturequizapi.domain.admin.command.application.AdminCommentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminCommentDeleteController {

    private final AdminCommentDeleteService adminCommentDeleteService;

    @DeleteMapping("/admin/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") String commentId){
        adminCommentDeleteService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

}
