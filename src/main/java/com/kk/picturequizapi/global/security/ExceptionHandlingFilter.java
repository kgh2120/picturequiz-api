package com.kk.picturequizapi.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import com.kk.picturequizapi.global.exception.LoginValidateErrorException;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kk.picturequizapi.global.exception.GlobalErrorCode.*;


public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException | AbstractApiException | CannotCreateTransactionException |
                 FileSizeLimitExceededException | SizeLimitExceededException e) {
            sendErrorResponse(e, request, response);

        }
    }

    private void sendErrorResponse(Throwable e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json; charset=utf-8");


        ErrorResponse errorResponse = null;
        if (e instanceof MalformedJwtException)
            errorResponse = ErrorResponse.createErrorResponse(JWT_MALFORMED, request.getRequestURI());
        if (e instanceof ExpiredJwtException)
            errorResponse = ErrorResponse.createErrorResponse(JWT_EXPIRED, request.getRequestURI());
        if (e instanceof UnsupportedJwtException)
            errorResponse = ErrorResponse.createErrorResponse(JWT_UNSUPPORTED, request.getRequestURI());
        if (e instanceof SignatureException)
            errorResponse = ErrorResponse.createErrorResponse(JWT_NOT_VERIFIED, request.getRequestURI());
        if (e instanceof LoginValidateErrorException) {
            response.setStatus(BIND_ERROR.getHttpStatus().value());
            errorResponse = ErrorResponse.createErrorResponse(BIND_ERROR, request.getRequestURI());
        }
        if (e instanceof CannotCreateTransactionException) {
            response.setStatus(DB_CONNECT_FAIL.getHttpStatus().value());
            errorResponse = ErrorResponse.createErrorResponse(DB_CONNECT_FAIL, request.getRequestURI());
        }
        if (e instanceof FileSizeLimitExceededException) {
            response.setStatus(FILE_SIZE_LIMIT_EXCEEDED.getHttpStatus().value());
            errorResponse = ErrorResponse.createErrorResponse(FILE_SIZE_LIMIT_EXCEEDED, request.getRequestURI());
        }
        if (e instanceof SizeLimitExceededException) {
            response.setStatus(FILE_SIZE_LIMIT_EXCEEDED.getHttpStatus().value());
            errorResponse = ErrorResponse.createErrorResponse(FILE_SIZE_LIMIT_EXCEEDED, request.getRequestURI());
        }







        ObjectMapper mapper = new ObjectMapper();
        String errorMsg = mapper.writeValueAsString(errorResponse);


        response.getWriter().write(errorMsg);
    }
}
