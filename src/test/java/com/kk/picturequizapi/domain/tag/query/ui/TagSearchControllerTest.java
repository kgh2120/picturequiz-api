package com.kk.picturequizapi.domain.tag.query.ui;

import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.domain.tag.query.application.TagSearchService;
import com.kk.picturequizapi.domain.users.exception.VerifyCodeInvalidException;
import com.kk.picturequizapi.global.config.GlobalExceptionHandler;
import com.kk.picturequizapi.global.config.SecurityConfig;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.kk.picturequizapi.domain.tag.TagTestUtil.createTag;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TagSearchController.class, GlobalExceptionHandler.class},
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
class TagSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TagSearchService service;

    @MockBean
    GlobalExceptionHandler exceptionHandler;
    // 태그 검색 성공
    @Test
    void searchTag_Success () throws Exception{
        //given
        given(service.findTagByName(any()))
                .willReturn(createTag().get());

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .get("/tag?name=tag"))
        //then
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("운동"))
                .andDo(print());
    
    }
    
    
    // 태그 검색 실패
    @Test
    void searchTag_NotFound () throws Exception{
        //given
        String path = "/tag?name=tag";
        given(service.findTagByName(any()))
                .willThrow(TagNotFoundException.class);

        given(exceptionHandler.handleGlobalException(any(),any()))
                .willReturn(ResponseEntity.status(404)
                        .body(ErrorResponse.createErrorResponse(new TagNotFoundException()
                                ,path)));

        //when

        mockMvc.perform(MockMvcRequestBuilders
                        .get(path))
                //then
                .andExpect(status().isNotFound())
                .andExpect( jsonPath("$.errorCode").value("t-0001"))
                .andExpect(jsonPath("$.errorName").value("TAG_NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage").value("태그가 존재하지 않습니다."))
                .andExpect(jsonPath("$.path").value(path))
                .andDo(print());

    }

}