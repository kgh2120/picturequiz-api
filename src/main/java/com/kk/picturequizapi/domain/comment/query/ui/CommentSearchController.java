package com.kk.picturequizapi.domain.comment.query.ui;


import com.kk.picturequizapi.domain.comment.query.application.CommentSearchService;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchRequest;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentSearchController {

    private final CommentSearchService commentSearchService;

    @GetMapping("/comments")
    public ResponseEntity<CommentSearchResult> retrieveComment(@RequestBody CommentSearchRequest dto){
        return ResponseEntity.ok(commentSearchService.retrieveComments(dto.getQuizId(), dto.getPageNum()));
    }

}
