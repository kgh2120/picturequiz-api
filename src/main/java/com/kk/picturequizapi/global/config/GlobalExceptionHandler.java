package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(AbstractApiException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(ex, request.getRequestURI()));
    }
}
