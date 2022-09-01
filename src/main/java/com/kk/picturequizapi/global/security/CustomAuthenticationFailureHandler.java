package com.kk.picturequizapi.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.global.exception.ErrorCode;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {
        log.info("[CustomAuthenticationFailureHandler] - 필터 예외 처리");
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse((ErrorCode) exception.getCause(), request.getRequestURI());
        String errorMessage = mapper.writeValueAsString(errorResponse);

        response.setStatus(404);
        response.setCharacterEncoding("UTF-8");
        response.getWriter()
                .println(errorMessage);
    }
}
