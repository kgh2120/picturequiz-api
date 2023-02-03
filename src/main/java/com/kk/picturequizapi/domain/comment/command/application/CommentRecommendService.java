package com.kk.picturequizapi.domain.comment.command.application;

import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRecommend;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRecommendRepository;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentRecommendService {


    private final CommentRepository commentRepository;
    private final CommentRecommendRepository commentRecommendRepository;


    public void recommend(String commentId) {
       getCommentRecommend(commentId).recommend();
    }

    public void notRecommend(String commentId) {
        getCommentRecommend(commentId).notRecommend();
    }

    private CommentRecommend getCommentRecommend(String commentId) {
        return commentRecommendRepository.findByCommentAndUserId(
                        CommentId.of(commentId),
                        UserId.of(getUserIdFromAuthentication()))
                .orElseGet(() -> createCommentRecommendEntity(commentId));
    }

    private CommentRecommend createCommentRecommendEntity(String commentId) {
        Comment comment = commentRepository.findById(CommentId.of(commentId))
                .orElseThrow();

        return commentRecommendRepository.save(
                CommentRecommend.of(commentRecommendRepository.nextId(),
                        getUserIdFromAuthentication(), comment));

    }


    private Long getUserIdFromAuthentication() {
        return ((Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
