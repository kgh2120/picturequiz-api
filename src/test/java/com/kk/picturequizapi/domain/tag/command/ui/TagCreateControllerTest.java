package com.kk.picturequizapi.domain.tag.command.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.tag.command.application.TagCreateRequest;
import com.kk.picturequizapi.domain.tag.command.application.TagCreateService;
import com.kk.picturequizapi.domain.tag.exception.TagNameDuplicateException;
import com.kk.picturequizapi.global.config.GlobalExceptionHandler;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.kk.picturequizapi.domain.GlobalExceptionHandlerTestUtil.createErrorResponse;
import static com.kk.picturequizapi.domain.tag.TagTestUtil.createTagSearch;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {TagCreateController.class, GlobalExceptionHandler.class},
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
class TagCreateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GlobalExceptionHandler handler;

    @MockBean
    TagCreateService service;



    @Test
    void createTag () throws Exception{
        //given
        TagCreateRequest r = new TagCreateRequest();
        r.setName("운동");
        r.setColor("#123123");
        given(service.createTag(any()))
                .willReturn(createTagSearch().get());

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(r)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("운동"))
                .andDo(print());

    
    }

    @Test
    void createTag_exception_duplicate () throws Exception{
        //given
        TagCreateRequest r = new TagCreateRequest();
        r.setName("운동");
        r.setColor("#123123");
        given(service.createTag(any()))
                .willThrow(TagNameDuplicateException.class);
        String path = "/tag";
        given(handler.handleGlobalException(any(), any()))
                .willReturn(createErrorResponse(new TagNameDuplicateException(), path));
        //when
        
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(r)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("t-0002"))
                .andExpect(jsonPath("$.errorName").value("TAG_NAME_DUPLICATE"))
                .andExpect(jsonPath("$.errorMessage").value("해당 이름은 이미 존재합니다."))
                .andExpect(jsonPath("$.path").value(path))
                .andDo(print());
    
    }

}