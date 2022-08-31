package com.kk.picturequizapi.domain.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.service.UserServiceImpl;
import com.kk.picturequizapi.global.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = {UserController.class, SecurityConfig.class})
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;

    ObjectMapper mapper = new ObjectMapper();

    final UserAccessRequestDto dto = UserAccessRequestDto.builder()
            .loginId("test")
            .password("password")
            .build();




    @Test
    void signUp () throws Exception{
        //given
        given(userService.signUp(any()))
                .willReturn(new SignUpResponseDto(1L));
        //when then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andDo(print());
    }
    
    @Test
    void login () throws Exception{
        //given
        given(userService.login(dto))
                .willReturn(TokenResponseDto.builder()
                        .accessToken("access")
                        .refreshToken("refresh")
                        .build());
        //when  //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken", is("access")))
                .andExpect(jsonPath("$.refreshToken", is("refresh")))
                .andDo(print());
    }

}