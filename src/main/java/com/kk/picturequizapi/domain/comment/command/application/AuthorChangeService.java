package com.kk.picturequizapi.domain.comment.command.application;

import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentRepository;
import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorChangeService {

    private final CommentRepository commentRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;


    public void changeAuthorNickname(long userId, String nickname) {
        changeNickname(userId, nickname);
        deleteAnonymousData(userId);
    }

    private void deleteAnonymousData(long userId) {
        anonymousCommentRepository.deleteAllByUserId(UserId.of(userId));
    }

    private void changeNickname(long userId, String nickname) {
        List<Comment> comments = commentRepository.findAllCommentsByUserId(
                UserId.of(userId));
        for (Comment comment : comments) {
            comment.changeAuthorNickname(nickname);
        }
    }
}
