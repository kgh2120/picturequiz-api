package com.kk.picturequizapi.domain.comment.command.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;


@Access(AccessType.FIELD)
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


    public boolean isRecommend() {
        return recommend;
    }

    public boolean isNotRecommend(){
        return notRecommend;
    }

    public RecommendStatus recommend(){
        RecommendStatus status = new RecommendStatus();
        status.recommend = !recommend;
        return status;
    }

    public RecommendStatus notRecommend(){
        RecommendStatus status = new RecommendStatus();
        status.notRecommend = !notRecommend;
        return status;
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
