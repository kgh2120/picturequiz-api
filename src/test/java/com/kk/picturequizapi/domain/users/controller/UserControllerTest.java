package com.kk.picturequizapi.domain.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.service.UserServiceImpl;
import com.kk.picturequizapi.global.config.SecurityConfig;
import com.kk.picturequizapi.global.jwt.JwtAuthorizationFilter;
import com.kk.picturequizapi.global.jwt.JwtProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = {UserController.class, SecurityConfig.class, JwtAuthorizationFilter.class})
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;
    @MockBean
    JwtProvider jwtProvider;
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    RefreshTokenService refreshTokenService;
    @MockBean
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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
        given(userService.loadUserByUsername(any()))
                .willReturn(new User(dto.getLoginId(), bCryptPasswordEncoder.encode(dto.getPassword()), new ArrayList<>()));
        given(authenticationManager.authenticate(any()))
                .willReturn(new UsernamePasswordAuthenticationToken(new User(dto.getLoginId(), bCryptPasswordEncoder.encode(dto.getPassword()), new ArrayList<>()), dto.getPassword()));
        given(jwtProvider.createAccessToken(any()))
                .willReturn("token");
        given(jwtProvider.createRefreshToken(any()))
                .willReturn("token");

        //when  //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Access-Token"))
                .andExpect(header().exists("Refresh-Token"))
                .andDo(print());
    }

}