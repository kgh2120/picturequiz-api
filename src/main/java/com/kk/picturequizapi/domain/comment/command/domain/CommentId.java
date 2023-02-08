package com.kk.picturequizapi.domain.comment.command.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Embeddable
public class CommentId implements Serializable {

    private String commentId;

    public static CommentId of (String commentId){
        return new CommentId(commentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentId commentId1 = (CommentId) o;
        return Objects.equals(commentId, commentId1.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
