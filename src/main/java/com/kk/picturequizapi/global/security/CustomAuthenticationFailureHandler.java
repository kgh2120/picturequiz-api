package com.kk.picturequizapi.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kk.picturequizapi.domain.users.exception.BlockUserLoginException;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.global.exception.ErrorCode;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.LOGIN_DATA_NOT_FOUND;
import static com.kk.picturequizapi.global.exception.GlobalErrorCode.DB_CONNECT_FAIL;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {
        log.info("[CustomAuthenticationFailureHandler] - 필터 예외 처리");
          /*
        BadCredentialsException : 아이디는 맞는데 비번을 틀렸을 때; -> JwtAuthenticationFilter에서 password를 credential 항목에 넣었음.
        CannotCreateTransactionException : DB와의 연결이 끊겨 Jpa 트랜잭션을 만들지 못할 때 발생
        UserIdNotFoundException : ID가 틀렸을 때
         */
        ErrorResponse errorResponse = null;
        if (exception instanceof BadCredentialsException) {
            errorResponse = ErrorResponse.createErrorResponse(LOGIN_DATA_NOT_FOUND,request.getRequestURI());
        }else{
            switch (exception.getCause().getClass().getSimpleName()){
                case "CannotCreateTransactionException" :
                    errorResponse = ErrorResponse.createErrorResponse(DB_CONNECT_FAIL,request.getRequestURI());
                    break;
                case "LoginDataNotFoundException" :
                    errorResponse = ErrorResponse.createErrorResponse(LOGIN_DATA_NOT_FOUND,request.getRequestURI());
                    break;
                case "BlockUserLoginException" :
                    errorResponse = ErrorResponse.createErrorResponse((BlockUserLoginException)exception.getCause(),request.getRequestURI());
                    break;
            }
        }
        if (errorResponse != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
            response.setStatus(errorResponse.getHttpStatus().value());
            response.getWriter().println(objectMapper.writeValueAsString(errorResponse));
        }
    }
}
