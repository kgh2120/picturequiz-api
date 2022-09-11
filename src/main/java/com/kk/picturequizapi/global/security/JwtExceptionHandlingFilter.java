package com.kk.picturequizapi.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kk.picturequizapi.global.exception.GlobalErrorCode.*;


public class JwtExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            sendErrorResponse(e, request, response);

        }
    }

    private void sendErrorResponse(JwtException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        ObjectMapper mapper = new ObjectMapper();
        String errorMsg = mapper.writeValueAsString(errorResponse);


        response.getWriter().write(errorMsg);
    }
}
