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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentCreateService {

    private final CommentRepository commentRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;
    private final QuizIdValidateService quizIdValidateService;

    public void createComment(CommentCreateRequest dto){
        quizIdValidateService.validateQuizId(dto.getQuizId());
        // quizId가 존재하는 것인지 체크하기.
        // 댓글인지 대댓글인지 체크.
        Long order;
        if (dto.getParentId() != null) {
            // parentId가 존재하는지 체크
            order = commentRepository.findParentCommentOrder(CommentId.of(dto.getParentId()))
                    .orElseThrow(ParentIdNotFoundException::new);
        } else {
            // 존재하지 않다면 lastsequence 따오기, 없으면 1을 갖자
            order = commentRepository.findLastCommentOrder(QuizId.of(dto.getQuizId()))
                    .orElseGet(() -> 0L) + 1;
        }


        // comment order 따오기.

        log.info("order = {}", order);


        // jwt 에서 정보 긁어 와서 닉네임 있는지 체크
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        String nickname = users.getNickname();

        if (nickname == null) {
            Long sequence  = anonymousCommentRepository.findUserSequence(
                            AnonymousCommentId.of(QuizId.of(dto.getQuizId()),
                                    UserId.of(users.getId())))
                    .orElseGet(() ->{
                                Long seq = anonymousCommentRepository.findLastSequence(
                                                QuizId.of(dto.getQuizId()))
                                        .orElseGet(() -> 0L);
                                anonymousCommentRepository.save(AnonymousComment.of(QuizId.of(dto.getQuizId())
                                        ,UserId.of(users.getId()), ++seq));
                                return seq;
                            }
                          );
            nickname = "익명" + sequence;
        }

        Comment comment = Comment.of(CommentId.of(anonymousCommentRepository.nextId())
                , CommentId.of(dto.getParentId()), QuizId.of(dto.getQuizId()), new Author(UserId.of(
                        users.getId()), nickname), CommentOrder.of(order),
                CommentContent.of(dto.getContents()));

        commentRepository.save(comment);

    }

}
