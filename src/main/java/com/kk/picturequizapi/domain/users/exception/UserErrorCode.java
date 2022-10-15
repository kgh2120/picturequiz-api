package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    LOGIN_DATA_NOT_FOUND(HttpStatus.NOT_FOUND,"U-0001","아이디 혹은 비밀번호가 알맞지 않습니다."),
    VERIFY_CODE_EXPIRED(HttpStatus.NOT_FOUND,"U-0002","인증 코드가 만료되었습니다."),
    VERIFY_CODE_INVALID(HttpStatus.CONFLICT,"U-0003","인증 코드가 잘못되었습니다."),

    LOGIN_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"U-0004","로그인 아이디가 잘못 되었습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }

}
