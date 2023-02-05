package com.kk.picturequizapi.domain.comment.command.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentCreateRequest {

    private String quizId;
    private String contents;
    private String parentId;

}
