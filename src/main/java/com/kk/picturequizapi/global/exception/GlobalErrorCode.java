package com.kk.picturequizapi.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum GlobalErrorCode implements ErrorCode{


    JWT_EXPIRED(HttpStatus.UNAUTHORIZED,"G-0001", "토큰이 만료되었습니다."), // v
    JWT_NOT_VERIFIED(HttpStatus.FORBIDDEN, "G-0002","토큰의 시그내처가 유효하지 않습니다."), // v
    JWT_MALFORMED(HttpStatus.FORBIDDEN, "G-0003","토큰의 형식이 잘못되었습니다. 토큰은 [header].[payload].[secret]의 형식이어야 합니다."), // v
    JWT_UNSUPPORTED(HttpStatus.FORBIDDEN, "G-0004","지원하지 않는 종류의 토큰입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
