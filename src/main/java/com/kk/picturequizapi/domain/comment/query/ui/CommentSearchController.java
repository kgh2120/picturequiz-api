package com.kk.picturequizapi.domain.comment.query.ui;


import com.kk.picturequizapi.domain.comment.query.application.CommentSearchService;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchRequest;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentSearchController {

    private final CommentSearchService commentSearchService;

    @GetMapping("/comments")
    public ResponseEntity<CommentSearchResult> retrieveComment(
            @RequestParam("quizId") String quizId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") @Min(0) int pageNum
            ){
        return ResponseEntity.ok(commentSearchService.retrieveComments(quizId, pageNum));
    }

}
