package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.DUPLICATE_LOGIN_ID;
import static com.kk.picturequizapi.global.exception.GlobalErrorCode.*;

@Slf4j
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
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {
        return ResponseEntity.status(MISSING_REQUEST_PARAM.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(MISSING_REQUEST_PARAM, request.getRequestURI()));
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex, HttpServletRequest request) {
        return ResponseEntity.status(BIND_ERROR.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(BIND_ERROR, request.getRequestURI()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleBindException(ConstraintViolationException ex, HttpServletRequest request) {
        return ResponseEntity.status(CONSTRAINT_VIOLATION.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(CONSTRAINT_VIOLATION, request.getRequestURI()));
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorResponse> handleConnectException(HttpServletRequest request) {
        return ResponseEntity.status(DB_CONNECT_FAIL.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(DB_CONNECT_FAIL, request.getRequestURI()));
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class) // unique or not null 에 걸릴때 터짐. 내가 실수 안했으면 다 잡힘
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, HttpServletRequest request) {
        return ResponseEntity.status(DUPLICATE_LOGIN_ID.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(DUPLICATE_LOGIN_ID, request.getRequestURI()));
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleFileSizeLimitExceededException(HttpServletRequest request) {
        return ResponseEntity.status(FILE_SIZE_LIMIT_EXCEEDED.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(FILE_SIZE_LIMIT_EXCEEDED, request.getRequestURI()));
    }
    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleSizeLimitExceededException(HttpServletRequest request) {
        return ResponseEntity.status(FILE_SIZE_LIMIT_EXCEEDED.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(FILE_SIZE_LIMIT_EXCEEDED, request.getRequestURI()));
    }




}
