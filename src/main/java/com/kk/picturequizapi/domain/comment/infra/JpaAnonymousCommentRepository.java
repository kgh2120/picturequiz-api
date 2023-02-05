package com.kk.picturequizapi.domain.comment.infra;

import com.kk.picturequizapi.domain.comment.command.domain.AnonymousComment;
import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaAnonymousCommentRepository extends JpaRepository<AnonymousComment, AnonymousCommentId> {



}
