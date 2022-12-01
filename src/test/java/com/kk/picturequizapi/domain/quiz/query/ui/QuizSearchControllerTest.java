package com.kk.picturequizapi.domain.quiz.query.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.quiz.query.application.MyQuizService;
import com.kk.picturequizapi.domain.quiz.query.application.QuizSearchService;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import com.kk.picturequizapi.global.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.kk.picturequizapi.TestFactory.createMockQuizSearchResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {QuizSearchController.class, },
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}

)
class QuizSearchControllerTest {

    @MockBean
    QuizSearchDao dao;
    @MockBean
    QuizSearchService quizSearchService;

    @MockBean
    MyQuizService service;

    @Autowired
    MockMvc mockMvc;
    
    @Test
    void search_anything () throws Exception{
        //given
        given(quizSearchService.findQuizzes(any()))
                .willReturn(new QuizSearchResponse(null,1,true));

        QuizSearchCondition cond = new QuizSearchCondition();
        cond.setPageNum(0);
        ObjectMapper mapper = new ObjectMapper();

        //when  //then
        mockMvc.perform(post("/quiz")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjY2MDAzMzU0LCJleHAiOjE2NjYwODk3NTR9.isUcKk0LPBUnQ2UbUYenf5gnkvz3v0VyLd0Kb88NcYI")
                .content(mapper.writeValueAsString(cond)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPageNum").value(1))
                .andExpect(jsonPath("$.hasNext").value(true))
                .andDo(print());
    }
    
    @Test
    void search_my_quiz () throws Exception{
        //given
        given(service.findMyQuizzes(anyInt()))
                .willReturn(createMockQuizSearchResponse());
        //when

        mockMvc.perform(get("/quiz/my?pageNum=0")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjY2MDAzMzU0LCJleHAiOjE2NjYwODk3NTR9.isUcKk0LPBUnQ2UbUYenf5gnkvz3v0VyLd0Kb88NcYI"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPageNum").value(1))
                .andExpect(jsonPath("$.hasNext").value(true))
                .andDo(print());

        //then
        
    
    }


}