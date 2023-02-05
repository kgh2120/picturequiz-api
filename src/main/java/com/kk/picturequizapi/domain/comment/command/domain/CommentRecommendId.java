package com.kk.picturequizapi.domain.comment.command.domain;

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

    private String commentRecommendId;


    public static CommentRecommendId of(String commentRecommendId){
        return new CommentRecommendId(commentRecommendId);
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
        return Objects.equals(commentRecommendId, that.commentRecommendId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentRecommendId);
    }
}
