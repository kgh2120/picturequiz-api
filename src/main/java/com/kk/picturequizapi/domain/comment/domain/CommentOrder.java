package com.kk.picturequizapi.domain.comment.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class CommentOrder implements Serializable {

    private Long orderNum;

    public static CommentOrder of(Long orderNum) {
        return new CommentOrder(orderNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentOrder that = (CommentOrder) o;
        return Objects.equals(orderNum, that.orderNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNum);
    }
}
