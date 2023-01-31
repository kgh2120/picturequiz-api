package com.kk.picturequizapi.domain.comment.command.application;

import com.kk.picturequizapi.domain.comment.command.domain.AnonymousComment;
import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentId;
import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentRepository;
import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentContent;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentOrder;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.comment.command.domain.QuizIdValidateService;
import com.kk.picturequizapi.domain.comment.exception.ParentIdNotFoundException;
import com.kk.picturequizapi.domain.quiz.command.domain.Author;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentCreateService {

    private final CommentRepository commentRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;
    private final QuizIdValidateService quizIdValidateService;

    @Value("${default.anonymous}")
    private String defaultNickname;

    public void createComment(CommentCreateRequest dto) {
        validateQuizId(dto);
        Long order = getQuizOrder(dto);
        Users users = getUserInfoFromToken();
        createEntityAndSave(dto, order, users);
    }

    private void createEntityAndSave(CommentCreateRequest dto, Long order, Users users) {
        commentRepository.save(createEntity(dto, order, users,
                getNicknameOrCreate(dto, users)));
    }

    private Comment createEntity(CommentCreateRequest dto, Long order, Users users,
            String nickname) {
        return Comment.of(CommentId.of(anonymousCommentRepository.nextId())
                , CommentId.of(dto.getParentId()), QuizId.of(dto.getQuizId()), new Author(UserId.of(
                        users.getId()), nickname), CommentOrder.of(order),
                CommentContent.of(dto.getContents()));
    }

    private Users getUserInfoFromToken() {
        return (Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }

    private String getNicknameOrCreate(CommentCreateRequest dto, Users users) {
        String nickname = users.getNickname();
        if (isBlankOrNull(nickname)) {
            nickname = createAnonymousNickname(dto, users);
        }
        return nickname;
    }

    private boolean isBlankOrNull(String nickname) {
        return !StringUtils.hasText(nickname);
    }

    private String createAnonymousNickname(CommentCreateRequest dto, Users users) {
        return defaultNickname + getAnonymousSequence(dto, users);
    }

    private Long getAnonymousSequence(CommentCreateRequest dto, Users users) {
        return findUserAnonymousSequence(dto, users)
                .orElseGet(() -> createUserAnonymousSequence(dto, users));
    }

    private Optional<Long> findUserAnonymousSequence(CommentCreateRequest dto, Users users) {
        return anonymousCommentRepository.findUserSequence(
                AnonymousCommentId.of(QuizId.of(dto.getQuizId()),
                        UserId.of(users.getId())));
    }

    private Long createUserAnonymousSequence(CommentCreateRequest dto, Users users) {
        return saveUserAnonymousSequence(dto, users, getQuizLastSequence(dto));
    }

    private Long saveUserAnonymousSequence(CommentCreateRequest dto, Users users, Long seq) {
        anonymousCommentRepository.save(
                AnonymousComment.of(QuizId.of(dto.getQuizId())
                        , UserId.of(users.getId()), ++seq));
        return seq;
    }

    private Long getQuizLastSequence(CommentCreateRequest dto) {
        return anonymousCommentRepository.findLastSequence(
                        QuizId.of(dto.getQuizId()))
                .orElseGet(() -> 0L);
    }

    private void validateQuizId(CommentCreateRequest dto) {
        quizIdValidateService.validateQuizId(dto.getQuizId());
    }

    private Long getQuizOrder(CommentCreateRequest dto) {
        if (isBlankOrNull(dto.getParentId()))
            return getLastQuizOrder(dto);
        return getParentQuizOrder(dto);

    }

    private long getLastQuizOrder(CommentCreateRequest dto) {
        return commentRepository.findLastCommentOrder(QuizId.of(dto.getQuizId()))
                .orElseGet(() -> 0L) + 1;
    }

    private Long getParentQuizOrder(CommentCreateRequest dto) {
        return commentRepository.findParentCommentOrder(CommentId.of(dto.getParentId()))
                .orElseThrow(ParentIdNotFoundException::new);
    }

}
