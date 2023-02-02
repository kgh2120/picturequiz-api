package com.kk.picturequizapi.domain.comment.query.dao;

import com.kk.picturequizapi.domain.comment.infra.QueryDslCommentDao;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentSearchDaoImpl implements
        CommentSearchDao {

    private final QueryDslCommentDao queryDslCommentDao;

    @Override
    public CommentSearchResult retrieveComments(QuizId quizId, UserId userId,int pageNum) {
        return queryDslCommentDao.retrieveComments(quizId,userId,pageNum);
    }
}
