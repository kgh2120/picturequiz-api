package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;

import static com.kk.picturequizapi.global.exception.GlobalErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(AbstractApiException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(ex, request.getRequestURI()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {
        return ResponseEntity.status(MISSING_REQUEST_PART.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(MISSING_REQUEST_PART, request.getRequestURI()));
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestPartException ex, HttpServletRequest request) {
        return ResponseEntity.status(MISSING_REQUEST_PARAM.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(MISSING_REQUEST_PARAM, request.getRequestURI()));
    }

}
