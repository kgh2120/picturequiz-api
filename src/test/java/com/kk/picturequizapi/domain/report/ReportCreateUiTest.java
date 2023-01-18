package com.kk.picturequizapi.domain.report;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.quiz.command.ui.QuizCreateController;
import com.kk.picturequizapi.domain.report.application.ReportCreateService;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import com.kk.picturequizapi.domain.report.ui.ReportCreateController;
import com.kk.picturequizapi.global.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = {ReportCreateController.class},
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class ReportCreateUiTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReportCreateService reportCreateService;

    @InjectMocks
    ReportCreateController reportCreateController;

    @Test
    void 신고_생성 () throws Exception{
        //given
        ReportCreateRequest dto = new ReportCreateRequest("foo", ReportType.ETC, "hello");
        //when
        mockMvc.perform(post("/reports")
                .header("Authorization", "Bearer Token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(print());

        //then


    }

}
