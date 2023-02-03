package com.kk.picturequizapi.domain.comment.command.ui;

import com.kk.picturequizapi.domain.comment.command.application.CommentRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentRecommendController {

    private final CommentRecommendService commentRecommendService;

    @PostMapping("/comments/{commentId}/recommend")
    public ResponseEntity<Void> recommend(@PathVariable("commentId") String commentId){

        commentRecommendService.recommend(commentId);


        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{commentId}/not-recommend")
    public ResponseEntity<Void> notRecommend(@PathVariable("commentId") String commentId){

        commentRecommendService.notRecommend(commentId);

        return ResponseEntity.ok().build();
    }

}
