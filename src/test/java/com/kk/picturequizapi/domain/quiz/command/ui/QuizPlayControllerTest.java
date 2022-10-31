package com.kk.picturequizapi.domain.quiz.command.ui;

import com.kk.picturequizapi.domain.quiz.command.application.QuizPlayService;
import com.kk.picturequizapi.domain.quiz.exception.QuizNotFoundByIdException;
import com.kk.picturequizapi.global.config.GlobalExceptionHandler;
import com.kk.picturequizapi.global.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static com.kk.picturequizapi.TestFactory.createMockPlayQuizResponse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {QuizPlayController.class, GlobalExceptionHandler.class},
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class QuizPlayControllerTest {


    @MockBean
    QuizPlayService playService;


    @Autowired
    MockMvc mvc;

    @Test
    void postPlayQuiz () throws Exception{
        //given
        given(playService.playQuiz(anyString()))
                .willReturn(createMockPlayQuizResponse());


        //when //then
        mvc.perform(post("/quiz/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("/mock"))
                .andDo(print());

        
    
    }
    @Test
    void postPlayQuiz_exception_quizNotFound () throws Exception{
        //given
        given(playService.playQuiz(anyString()))
                .willThrow(new QuizNotFoundByIdException());
        //when  //then
        mvc.perform(post("/quiz/123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
                .andExpect(jsonPath("$.errorCode").value("q-0008"))
                .andExpect(jsonPath("$.errorName").value("QUIZ_NOT_FOUND_BY_ID"))
                .andExpect(jsonPath("$.errorMessage").value("ID에 매칭되는 퀴즈가 존재하지 않습니다."))
                .andExpect(jsonPath("$.path").value("/quiz/123"))
                .andDo(print());



    }


}