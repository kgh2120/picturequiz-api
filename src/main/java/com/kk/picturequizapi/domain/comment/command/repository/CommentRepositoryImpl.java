package com.kk.picturequizapi.domain.comment.command.repository;

import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.comment.infra.JpaCommentRepository;
import com.kk.picturequizapi.domain.comment.infra.QueryDslCommentDao;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final QueryDslCommentDao queryDslCommentDao;

    @Override
    public void save(Comment comment) {
        jpaCommentRepository.save(comment);
    }

    @Override
    public Optional<Long> findParentCommentOrder(CommentId commentId) {
        return queryDslCommentDao.findParentOrder(commentId);
    }

    @Override
    public Optional<Long> findLastCommentOrder(QuizId quizId) {
        return queryDslCommentDao.findQuizLastOrder(quizId);
    }

    @Override
    public Optional<Comment> findById(CommentId commentId) {
        return jpaCommentRepository.findById(commentId);
    }

    @Override
    public boolean existsById(CommentId commentId) {
        return jpaCommentRepository.existsById(commentId);
    }
}
