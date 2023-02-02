package com.kk.picturequizapi.domain.comment.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentSearch {


    private String commentId;

    private String parentId;
    private Long authorId;
    private String authorNickname;
    private String content;
    private long orderNum;
    private long numOfRecommend;
    private long numOfNotRecommend;
    private boolean recommend;
    private boolean notRecommend;

    @QueryProjection
    public CommentSearch(String commentId, String parentId, Long authorId, String authorNickname,
            String content, long orderNum,long numOfRecommend, long numOfNotRecommend, boolean recommend,
            boolean notRecommend) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.content = content;
        this.numOfRecommend = numOfRecommend;
        this.numOfNotRecommend = numOfNotRecommend;
        this.recommend = recommend;
        this.notRecommend = notRecommend;
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "CommentSearch{" +
                "commentId='" + commentId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", authorId=" + authorId +
                ", authorNickname='" + authorNickname + '\'' +
                ", content='" + content + '\'' +
                ", numOfRecommend=" + numOfRecommend +
                ", numOfNotRecommend=" + numOfNotRecommend +
                ", recommend=" + recommend +
                ", notRecommend=" + notRecommend +
                '}';
    }
}
