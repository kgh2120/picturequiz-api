package com.kk.picturequizapi.domain.admin.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReportedComment {
    private String commentId;
    private String nickname;
    private String contents;

    @QueryProjection
    public ReportedComment(String commentId, String nickname, String contents) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.contents = contents;
    }
}
