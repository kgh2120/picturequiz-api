package com.kk.picturequizapi.domain.comment.infra;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRecommend;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRecommendId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRecommendRepository extends JpaRepository<CommentRecommend, CommentRecommendId> {

    Optional<CommentRecommend> findByComment_CommentIdAndUserId(CommentId commentId, UserId userId);

}
