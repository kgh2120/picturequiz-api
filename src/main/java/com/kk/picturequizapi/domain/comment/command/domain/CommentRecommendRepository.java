package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Optional;
import java.util.UUID;

public interface CommentRecommendRepository {

    CommentRecommend save(CommentRecommend commentRecommend);

    Optional<CommentRecommend> findByCommentAndUserId(CommentId commentId, UserId userId);

    default String nextId(){
        return UUID.randomUUID().toString();
    }

}
