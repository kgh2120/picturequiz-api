package com.kk.picturequizapi.domain.comment.command.repository;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRecommend;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRecommendRepository;
import com.kk.picturequizapi.domain.comment.infra.JpaCommentRecommendRepository;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRecommendRepositoryImpl implements CommentRecommendRepository {

    private final JpaCommentRecommendRepository jpaCommentRecommendRepository;

    @Override
    public CommentRecommend save(CommentRecommend commentRecommend) {
        return jpaCommentRecommendRepository.save(commentRecommend);
    }

    @Override
    public Optional<CommentRecommend> findByCommentAndUserId(CommentId commentId, UserId userId) {
        return jpaCommentRecommendRepository.findByComment_CommentIdAndUserId(commentId,userId);
    }
}
