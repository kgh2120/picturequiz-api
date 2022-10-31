package com.kk.picturequizapi.domain.quiz.query.application;

import com.kk.picturequizapi.domain.quiz.command.domain.AuthorService;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.kk.picturequizapi.TestFactory.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MyQuizServiceTest {

    @Mock
    AuthorService authorService;
    @Mock
    QuizSearchDao quizSearchDao;

    @InjectMocks MyQuizService service;

    private void setSecurity() throws Exception {

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        createUser("author","password"),"password",null));
    }

    @Test
    void readMyQuiz () throws Exception{
        //given
        setSecurity();
        given(authorService.createAuthor(any()))
                .willReturn(createMockAuthor());
        given(quizSearchDao.
                searchMyQuizzes(
                any(),
               anyInt()))
                .willReturn(createMockQuizSearchResponse());
        //when
        QuizSearchResponse response = service.findMyQuizzes(0);
        //then
        assertThat(response.getQuizzes().size()).isSameAs(3);
        assertThat(response.getNextPageNum()).isSameAs(1);
        assertThat(response.isHasNext()).isTrue();

    }

}