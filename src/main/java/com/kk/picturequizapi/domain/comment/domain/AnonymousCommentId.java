package com.kk.picturequizapi.domain.comment.domain;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
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
public class AnonymousCommentId implements Serializable {

    private QuizId quizId;
    private UserId userId;


    public static AnonymousCommentId of(QuizId quizId, UserId userId){
        return new AnonymousCommentId(quizId,userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnonymousCommentId that = (AnonymousCommentId) o;
        return Objects.equals(quizId, that.quizId) && Objects.equals(userId,
                that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, userId);
    }
}
