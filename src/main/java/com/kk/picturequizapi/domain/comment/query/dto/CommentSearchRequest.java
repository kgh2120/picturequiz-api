package com.kk.picturequizapi.domain.comment.query.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentSearchRequest {

    private String quizId;
    private int pageNum;
}
