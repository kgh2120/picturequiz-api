package com.kk.picturequizapi.domain.comment.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class RecommendStatus implements Serializable {

    private Boolean recommend;
    private Boolean notRecommend;

    public RecommendStatus(){
        recommend = false;
        notRecommend = false;
    }

    public static RecommendStatus of(){
        return new RecommendStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecommendStatus that = (RecommendStatus) o;
        return Objects.equals(recommend, that.recommend) && Objects.equals(
                notRecommend, that.notRecommend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recommend, notRecommend);
    }
}
