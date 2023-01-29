package com.kk.picturequizapi.domain.comment.domain;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class AnonymousComment {


    @EmbeddedId
    private AnonymousCommentId anonymousCommentId;

    @Embedded
    private AnonymousSequence anonymousSequence;

    public static AnonymousComment of(QuizId quizId, UserId userId, Long sequence){

        return new AnonymousComment(AnonymousCommentId.of(quizId,userId)
        ,AnonymousSequence.of(sequence));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnonymousComment that = (AnonymousComment) o;
        return Objects.equals(anonymousCommentId, that.anonymousCommentId)
                && Objects.equals(anonymousSequence, that.anonymousSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anonymousCommentId, anonymousSequence);
    }
}
