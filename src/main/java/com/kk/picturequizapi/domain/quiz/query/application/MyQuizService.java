package com.kk.picturequizapi.domain.quiz.query.application;

import com.kk.picturequizapi.domain.quiz.command.domain.Author;
import com.kk.picturequizapi.domain.quiz.command.domain.AuthorService;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyQuizService {

    private final AuthorService authorService;
    private final QuizSearchDao quizSearchDao;


    public QuizSearchResponse findMyQuizzes(int pageNum) {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Author author = authorService.createAuthor(loginId);

        return quizSearchDao.searchMyQuizzes(author.getUserId(), pageNum);
    }
}
