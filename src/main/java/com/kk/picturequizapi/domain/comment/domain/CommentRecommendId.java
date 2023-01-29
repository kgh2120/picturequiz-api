package com.kk.picturequizapi.domain.comment.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class CommentRecommendId implements Serializable {

    private CommentId commentId;
    private UserId userId;

    public static CommentRecommendId of(CommentId commentId, UserId userId){
        return new CommentRecommendId(commentId,userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentRecommendId that = (CommentRecommendId) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(userId,
                that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId);
    }
}
