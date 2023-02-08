package com.kk.picturequizapi.domain.comment.command.application;

import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.comment.exception.CommentIdNotFoundException;
import com.kk.picturequizapi.domain.comment.exception.CommentNotYoursException;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.global.event.CommentDeletedEvent;
import com.kk.picturequizapi.global.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteMyCommentService {

    private final CommentRepository commentRepository;

    public void deleteMyComment(String commentId) {
        deleteComment(commentId, findComment(commentId));
        Events.raise(new CommentDeletedEvent(commentId));
    }


    private Comment findComment(String commentId) {
        return commentRepository.findById(CommentId.of(commentId))
                .orElseThrow(CommentIdNotFoundException::new);
    }

    private void deleteComment(String commentId, Comment comment) {
        validateCanDelete(comment);
        if (comment.isParentComment()) {
            comment.delete();
            return;
        }
        commentRepository.deleteById(CommentId.of(commentId));
    }

    private void validateCanDelete(Comment comment) {
        comment.validateAccessAuthority(getUserId());
    }

    private Long getUserId() {
        return ((Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getId();
    }
}
