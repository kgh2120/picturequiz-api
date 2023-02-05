package com.kk.picturequizapi.domain.comment.command.ui;

import com.kk.picturequizapi.domain.comment.command.application.CommentCreateRequest;
import com.kk.picturequizapi.domain.comment.command.application.CommentCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentCreateController {

    private final CommentCreateService commentCreateService;


    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(@RequestBody CommentCreateRequest dto){
        commentCreateService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
