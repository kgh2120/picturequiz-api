package com.kk.picturequizapi.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.global.exception.LoginValidateErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j @RequiredArgsConstructor
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("[JWTAuthorizationFilter] - attemptAuthentication 시작");

        ObjectMapper mapper = new ObjectMapper();
        UserAccessRequestDto dto = null;
        try {
            dto = mapper.readValue(request.getInputStream(),UserAccessRequestDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!LoginValidator.validateLogin(dto.getLoginId(), dto.getPassword())){
            throw new LoginValidateErrorException();
        }
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(dto.getLoginId(), dto.getPassword());
        return authenticationManager.authenticate(authenticationToken);

    }

    // jwt 토큰을 만들어서 response로 전달해줘서 유저가 받아서 사용할 수 있게끔 한다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("[JWTAuthorizationFilter] - successfulAuthentication 시작");
        Users user = (Users) authResult.getPrincipal();
        log.info("[JWTAuthorizationFilter] - User = {}", user);
        String accessToken = jwtProvider.createAccessToken(user.getUsername());
        String refreshToken = jwtProvider.createRefreshToken(user.getUsername());

        TokenResponseDto dto = TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        refreshTokenService.createToken(user.getUsername(),refreshToken);

        response.addHeader("Access-Token",dto.getAccessToken());
        response.addHeader("Refresh-Token",dto.getRefreshToken());
        response.setStatus(201);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("[JWTAuthorizationFilter] - 인증실패");
        authenticationFailureHandler.onAuthenticationFailure(request,response,failed);
    }
}
