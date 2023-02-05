package com.kk.picturequizapi.domain.comment.command.application;


import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentRepository;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ClearCommentService {

    private final CommentRepository commentRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;

    public void clearComment(String quizId) {
        clearAllAboutQuiz(QuizId.of(quizId));
    }

    private void clearAllAboutQuiz(QuizId quizId) {
        commentRepository.clearCommentsOnQuiz(quizId);
        anonymousCommentRepository.clearAnonymousComment(quizId);
    }
}
