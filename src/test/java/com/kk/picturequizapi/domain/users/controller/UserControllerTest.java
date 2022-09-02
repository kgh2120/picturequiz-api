package com.kk.picturequizapi.domain.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.dto.MyInfoResponseDto;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.domain.users.service.UserServiceImpl;
import com.kk.picturequizapi.global.config.SecurityConfig;
import com.kk.picturequizapi.global.security.CustomAuthenticationFailureHandler;
import com.kk.picturequizapi.global.security.JwtAuthenticationFilter;
import com.kk.picturequizapi.global.security.JwtAuthorizationFilter;
import com.kk.picturequizapi.global.security.JwtProvider;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.security.sasl.AuthenticationException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = {UserController.class, SecurityConfig.class, JwtAuthorizationFilter.class
        , JwtAuthenticationFilter.class, CustomAuthenticationFailureHandler.class})
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
    void login_exception_id_not_found () throws Exception{
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
                .andExpect(jsonPath("$.errorMessage", is("아이디 혹은 비밀번호가 알맞지 않습니다.")))
                .andDo(print());

        
    
    }
    @Test
    void login_exception_password_mismatch () throws Exception{
        //given
        given(userService.loadUserByUsername(any()))
                .willReturn(createUser("test","pass"));
        given(authenticationManager.authenticate(any()))
                .willThrow(new InternalAuthenticationServiceException("로그인 데이터가 없어용",new LoginDataNotFoundException()));
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.errorCode", is("U-0001")))
                .andExpect(jsonPath("$.errorName", is("LOGIN_DATA_NOT_FOUND")))
                .andExpect(jsonPath("$.errorMessage", is("아이디 혹은 비밀번호가 알맞지 않습니다.")))
                .andDo(print());
        
    
    }
    
    @Test
    void readMyInfo () throws Exception{
        //given
        given(jwtProvider.validateToken(any()))
                .willReturn(Jwts.claims().setSubject("test"));
        Users user = createUser("test", "password");
        given(userService.loadUserByUsername(any()))
                .willReturn(user);
        given(userService.readMyInfo())
                .willReturn(MyInfoResponseDto.createDto(user));

        
        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/my-profile").header("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjYxOTk1Njc0LCJleHAiOjE2NjIwODIwNzR9.XQYZmVLvkBL8v1rRXCgLpy1LB2sLSKw21hQy3jPDbxw"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginId", is("test")))
                .andExpect(jsonPath("$.nickname", nullValue()))
                .andExpect(jsonPath("$.authEmail", nullValue()))
                .andDo(print());

        
    
    }
    private Users createUser(String id, String pwd) throws Exception {
        Users user = Users.createUserEntity(id, bCryptPasswordEncoder.encode(pwd));
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);
        return user;
    }
    

}