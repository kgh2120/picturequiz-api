package com.kk.picturequizapi.domain.comment.command.repository;

import com.kk.picturequizapi.domain.comment.command.domain.AnonymousComment;
import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentId;
import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentRepository;
import com.kk.picturequizapi.domain.comment.infra.JpaAnonymousCommentRepository;
import com.kk.picturequizapi.domain.comment.infra.QueryDslAnonymousCommentDao;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AnonymousCommentRepositoryImpl implements AnonymousCommentRepository {

    private final JpaAnonymousCommentRepository jpaAnonymousCommentRepository;
    private final QueryDslAnonymousCommentDao queryDslAnonymousCommentDao;

    @Override
    public Long save(AnonymousComment anonymousComment) {
        return jpaAnonymousCommentRepository.save(anonymousComment)
                .getSequence();
    }

    @Override
    public Optional<Long> findUserSequence(AnonymousCommentId anonymousCommentId) {
        return queryDslAnonymousCommentDao.findUserSequence(anonymousCommentId);
    }

    @Override
    public Optional<Long> findLastSequence(QuizId quizId) {
        return queryDslAnonymousCommentDao.findLastSequence(quizId);
    }

    @Override
    public void clearAnonymousComment(QuizId quizId) {
        queryDslAnonymousCommentDao.clearAnonymousDate(quizId);
    }

    @Override
    public void deleteAllByUserId(UserId userId) {
        queryDslAnonymousCommentDao.deleteAllByUserId(userId);
    }
}
