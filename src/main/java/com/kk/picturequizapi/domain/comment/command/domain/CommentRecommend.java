package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class CommentRecommend {

    @EmbeddedId
    private CommentRecommendId commentRecommendId;

    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @Embedded
    private UserId userId;

    @Embedded
    private RecommendStatus recommendStatus;

    public CommentRecommend(CommentRecommendId commentRecommendId, UserId userId, Comment comment){
        this.commentRecommendId = commentRecommendId;
        this.userId = userId;
        this.recommendStatus = RecommendStatus.of();
        this.comment = comment;
        comment.associatedWith(this);
    }
    public static CommentRecommend of(String recommendId, Long userId, Comment comment){
        return new CommentRecommend(CommentRecommendId.of(recommendId), UserId.of(userId), comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentRecommend that = (CommentRecommend) o;
        return Objects.equals(commentRecommendId, that.commentRecommendId)
                && Objects.equals(recommendStatus, that.recommendStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentRecommendId, recommendStatus);
    }
}
