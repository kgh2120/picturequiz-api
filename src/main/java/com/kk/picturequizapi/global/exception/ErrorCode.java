package com.kk.picturequizapi.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();
    String getErrorCode();
    String getErrorName();
    String getErrorMessage();
}
