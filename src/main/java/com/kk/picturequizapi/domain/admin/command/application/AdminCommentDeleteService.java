package com.kk.picturequizapi.domain.admin.command.application;

import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.comment.exception.CommentIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AdminCommentDeleteService {

    private final CommentRepository commentRepository;

    public void deleteComment(String commentId) {
        deleteComment(commentId, findComment(commentId));
    }

    private Comment findComment(String commentId) {
        return commentRepository.findById(CommentId.of(commentId))
                .orElseThrow(CommentIdNotFoundException::new);
    }

    private void deleteComment(String commentId, Comment comment) {
        if (comment.isParentComment()) {
            comment.delete();
            return;
        }
        commentRepository.deleteById(CommentId.of(commentId));
    }
}
