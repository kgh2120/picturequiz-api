package com.kk.picturequizapi.domain.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.dto.*;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.domain.users.exception.VerifyCodeExpiredException;
import com.kk.picturequizapi.domain.users.exception.VerifyCodeInvalidException;
import com.kk.picturequizapi.domain.users.service.UserServiceImpl;
import com.kk.picturequizapi.domain.users.service.VerificationService;
import com.kk.picturequizapi.domain.users.service.VerificationServiceImpl;
import com.kk.picturequizapi.global.config.GlobalExceptionHandler;
import com.kk.picturequizapi.global.config.SecurityConfig;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import com.kk.picturequizapi.global.security.CustomAuthenticationFailureHandler;
import com.kk.picturequizapi.global.security.JwtAuthenticationFilter;
import com.kk.picturequizapi.global.security.JwtAuthorizationFilter;
import com.kk.picturequizapi.global.security.JwtProvider;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = {UserController.class, SecurityConfig.class, JwtAuthorizationFilter.class
        , JwtAuthenticationFilter.class, CustomAuthenticationFailureHandler.class
        , GlobalExceptionHandler.class})
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;
    @MockBean
    VerificationServiceImpl verificationService;
    @MockBean
    JwtProvider jwtProvider;
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    RefreshTokenService refreshTokenService;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    GlobalExceptionHandler exceptionHandler;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    ObjectMapper mapper = new ObjectMapper();

    final UserAccessRequestDto dto = UserAccessRequestDto.builder()
            .loginId("test")
            .password("password")
            .build();

    @Test
    void signUp() throws Exception {
        //given
        given(userService.signUp(any()))
                .willReturn(new SignUpResponseDto(1L));
        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andDo(print());
    }

    @Test
    void login() throws Exception {
        //given
        Users user = createUser(dto.getLoginId(), dto.getPassword());
        given(userService.loadUserByUsername(any()))
                .willReturn(user);
        given(authenticationManager.authenticate(any()))
                .willReturn(new UsernamePasswordAuthenticationToken(user, dto.getPassword()));
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

    @Test
    void login_exception_id_not_found() throws Exception {
        //given
        given(userService.loadUserByUsername(any()))
                .willThrow(new LoginDataNotFoundException());
        given(authenticationManager.authenticate(any()))
                .willThrow(new LoginDataNotFoundException());

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.errorCode", is("U-0001")))
                .andExpect(jsonPath("$.errorName", is("LOGIN_DATA_NOT_FOUND")))
                .andExpect(jsonPath("$.errorMessage", is("????????? ?????? ??????????????? ????????? ????????????.")))
                .andDo(print());


    }

    @Test
    void login_exception_password_mismatch() throws Exception {
        //given
        given(userService.loadUserByUsername(any()))
                .willReturn(createUser("test", "pass"));
        given(authenticationManager.authenticate(any()))
                .willThrow(new InternalAuthenticationServiceException("????????? ???????????? ?????????", new LoginDataNotFoundException()));
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.errorCode", is("U-0001")))
                .andExpect(jsonPath("$.errorName", is("LOGIN_DATA_NOT_FOUND")))
                .andExpect(jsonPath("$.errorMessage", is("????????? ?????? ??????????????? ????????? ????????????.")))
                .andDo(print());


    }

    @Test
    void readMyInfo() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);
        given(userService.readMyInfo())
                .willReturn(MyInfoResponseDto.createDto(user));


        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/my-profile").header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjYxOTk1Njc0LCJleHAiOjE2NjIwODIwNzR9.XQYZmVLvkBL8v1rRXCgLpy1LB2sLSKw21hQy3jPDbxw"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginId", is("test")))
                .andExpect(jsonPath("$.nickname", nullValue()))
                .andExpect(jsonPath("$.authEmail", nullValue()))
                .andDo(print());
    }

    @Test
    void checkNicknameDuplicate_TRUE() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);
        given(userService.isExistNickname(any()))
                .willReturn(true);
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/my-profile/nickname?nickname=nickname")
                        .header("Authorization", "Bearer Token"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }

    @Test
    void checkNicknameDuplicate_FALSE() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);
        given(userService.isExistNickname(any()))
                .willReturn(false);
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/my-profile/nickname?nickname=nickname")
                        .header("Authorization", "Bearer Token"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"))
                .andDo(print());
    }

    @Test
    void changeNickname() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);

        ChangeNicknameRequestDto dto = new ChangeNicknameRequestDto("nickname");
        String content = mapper.writeValueAsString(dto);


        //when
        mockMvc.perform(MockMvcRequestBuilders.patch("/my-profile/nickname")
                        .header("Authorization", "Bearer Token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))

                //then

                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void sendMail() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);

        MailSendRequestDto dto = new MailSendRequestDto();
        dto.setEmail("kgh2120@naver.com");


        //when


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/my-profile/send-mail")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(request().asyncStarted())
                .andReturn();
        //then
        mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(mvcResult))
                .andExpect(status().isNoContent())
                .andDo(print());


    }

    @Test
    void verifyCode() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);

        CodeVerificationRequestDto dto = new CodeVerificationRequestDto();
        dto.setCode("code");
        dto.setEmail("test@naver.com");
        //when
        mockMvc.perform(MockMvcRequestBuilders.patch("/my-profile/verify-code")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                //then
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void verifyCode_Exception_VerifyCodeExpiredException() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);

        CodeVerificationRequestDto dto = new CodeVerificationRequestDto();
        dto.setCode("code");
        dto.setEmail("test@naver.com");

        doThrow(VerifyCodeExpiredException.class)
                .when(verificationService)
                .verifyCode(any(), any());

        String path = "/my-profile/verify-code";
        given(exceptionHandler.handleGlobalException(any(),any()))
                .willReturn(ResponseEntity.status(404)
                        .body(ErrorResponse.createErrorResponse(new VerifyCodeExpiredException()
                        ,path)));


        //when
        mockMvc.perform(MockMvcRequestBuilders.patch(path)
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                //then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.errorCode", is("U-0002")))
                .andExpect(jsonPath("$.errorName", is("VERIFY_CODE_EXPIRED")))
                .andExpect(jsonPath("$.errorMessage", is("?????? ????????? ?????????????????????.")))
                .andExpect(jsonPath("$.path", is(path)))
                .andDo(print());


    }

    @Test
    void verifyCode_Exception_VerifyCodeInvalidException() throws Exception {
        //given
        Users user = createUser("test", "password");
        setSecurityAfterLogin(user);

        CodeVerificationRequestDto dto = new CodeVerificationRequestDto();
        dto.setCode("code");
        dto.setEmail("test@naver.com");

        doThrow(VerifyCodeInvalidException.class)
                .when(verificationService)
                .verifyCode(any(), any());

        String path = "/my-profile/verify-code";
        given(exceptionHandler.handleGlobalException(any(),any()))
                .willReturn(ResponseEntity.status(409)
                        .body(ErrorResponse.createErrorResponse(new VerifyCodeInvalidException()
                                ,path)));


        //when
        mockMvc.perform(MockMvcRequestBuilders.patch(path)
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                //then
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.httpStatus", is("CONFLICT")))
                .andExpect(jsonPath("$.errorCode", is("U-0003")))
                .andExpect(jsonPath("$.errorName", is("VERIFY_CODE_INVALID")))
                .andExpect(jsonPath("$.errorMessage", is("?????? ????????? ?????????????????????.")))
                .andExpect(jsonPath("$.path", is(path)))
                .andDo(print());


    }


    private void setSecurityAfterLogin(Users user) {
        given(jwtProvider.validateToken(any()))
                .willReturn(Jwts.claims().setSubject("test"));
        given(userService.loadUserByUsername(any()))
                .willReturn(user);
    }

    private Users createUser(String id, String pwd) throws Exception {
        Users user = Users.createUserEntity(id, bCryptPasswordEncoder.encode(pwd));
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);
        return user;
    }


}