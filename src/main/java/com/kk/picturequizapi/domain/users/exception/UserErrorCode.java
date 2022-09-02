package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    LOGIN_DATA_NOT_FOUND(HttpStatus.NOT_FOUND,"U-0001","아이디 혹은 비밀번호가 알맞지 않습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }

}