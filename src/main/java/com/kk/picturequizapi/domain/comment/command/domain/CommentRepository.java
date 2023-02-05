package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {

    void save (Comment comment);

    Optional<Long> findParentCommentOrder(CommentId commentId);

    Optional<Long> findLastCommentOrder(QuizId quizId);

    Optional<Comment> findById(CommentId commentId);

    boolean existsById(CommentId commentId);

    void clearCommentsOnQuiz(QuizId quizId);

    default String nextId(){
        return UUID.randomUUID().toString();
    }

}
