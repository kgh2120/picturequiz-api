package com.kk.picturequizapi.domain.comment.infra;

import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaCommentRepository extends JpaRepository<Comment, CommentId> {


}
