package com.kk.picturequizapi.domain.comment.query.application;


import com.kk.picturequizapi.domain.comment.query.dao.CommentSearchDao;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentSearchService {

    private final CommentSearchDao commentSearchDao;

    public CommentSearchResult retrieveComments(String quizId, int pageNum){
        Long id = ((Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getId();

        return commentSearchDao.retrieveComments(QuizId.of(quizId),
                UserId.of(id), pageNum);

    }

}
