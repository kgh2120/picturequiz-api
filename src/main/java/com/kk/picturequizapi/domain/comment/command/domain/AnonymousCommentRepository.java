package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import java.util.Optional;
import java.util.UUID;

public interface AnonymousCommentRepository {

    Long save(AnonymousComment anonymousComment);

    Optional<Long> findUserSequence(AnonymousCommentId anonymousCommentId);

    Optional<Long> findLastSequence(QuizId quizId);

    default String nextId(){
        return UUID.randomUUID().toString();
    }

}
