package com.kk.picturequizapi.domain.comment.command.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;


@Embeddable
public class Recommend implements Serializable {

    private Long numOfRecommend;
    private Long numOfNotRecommend;

    public Recommend(){
        numOfRecommend = 0L;
        numOfNotRecommend = 0L;
    }

    public static Recommend of(){
        return new Recommend();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Recommend recommend = (Recommend) o;
        return Objects.equals(numOfRecommend, recommend.numOfRecommend)
                && Objects.equals(numOfNotRecommend, recommend.numOfNotRecommend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfRecommend, numOfNotRecommend);
    }
}
