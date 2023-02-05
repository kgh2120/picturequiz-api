package com.kk.picturequizapi.domain.comment.query.dao;

import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;

public interface CommentSearchDao {

    CommentSearchResult retrieveComments(QuizId quizId, UserId userId, int pageNum);

}
