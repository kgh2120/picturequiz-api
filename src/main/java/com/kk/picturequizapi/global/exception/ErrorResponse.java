package com.kk.picturequizapi.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorName;
    private String errorMessage;

    public static ErrorResponse createErrorResponse(ErrorCode errorCode) {
        return new ErrorResponse(LocalDateTime.now(),errorCode.getHttpStatus(),errorCode.getErrorCode()
        , errorCode.getErrorName(), errorCode.getErrorMessage());
    }
}
