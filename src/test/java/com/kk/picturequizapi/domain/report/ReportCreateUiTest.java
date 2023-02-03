package com.kk.picturequizapi.domain.report;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.quiz.command.ui.QuizCreateController;
import com.kk.picturequizapi.domain.quiz.exception.QuizNotFoundByIdException;
import com.kk.picturequizapi.domain.report.application.ReportCreateService;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import com.kk.picturequizapi.domain.report.exception.AlreadyReportedQuizException;
import com.kk.picturequizapi.domain.report.ui.ReportCreateController;
import com.kk.picturequizapi.global.config.GlobalExceptionHandler;
import com.kk.picturequizapi.global.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
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

@WebMvcTest(controllers = {ReportCreateController.class, GlobalExceptionHandler.class},
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
        ReportCreateRequest dto = new ReportCreateRequest("foo", TargetType.QUIZ, ReportType.ETC, "hello");
        //when
        mockMvc.perform(post("/reports")
                .header("Authorization", "Bearer Token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(print());

        //then
    }

    @Test
    void 신고_생성_중복_예외 () throws Exception{
        //given
        ReportCreateRequest dto = new ReportCreateRequest("foo",TargetType.QUIZ,  ReportType.ETC, "hello");

        doThrow(new AlreadyReportedQuizException())
                .when(reportCreateService).createReport(dto);

        //when  //then
        mockMvc.perform(post("/reports")
                        .header("Authorization", "Bearer Token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorCode").value("r-0005"))
                .andExpect(jsonPath("$.errorName").value("ALREADY_REPORTED_QUIZ"))
                .andExpect(jsonPath("$.errorMessage").value("해당 사항으로 이미 신고가 접수되었습니다."))
                .andExpect(jsonPath("$.path").value("/reports"))
                .andDo(print());



    }

}
