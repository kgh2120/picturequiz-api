package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class CommentRecommend {

    @EmbeddedId
    private CommentRecommendId commentRecommendId;

    @Embedded
    private RecommendStatus recommendStatus;

    public static CommentRecommend of(CommentId commentId, UserId userId){
        return new CommentRecommend(CommentRecommendId.of(commentId, userId)
        ,RecommendStatus.of());
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
