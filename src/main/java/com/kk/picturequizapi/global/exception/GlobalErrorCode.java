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

    MISSING_REQUEST_PART(HttpStatus.BAD_REQUEST,"G-0005","이미지 파일 혹은 JSON이 전달되지 않았습니다."),
    MISSING_REQUEST_PARAM(HttpStatus.BAD_REQUEST,"G-0006","리퀘스트 파라미터가 누락되었습니다."),

    BIND_ERROR(HttpStatus.BAD_REQUEST, "G-0007","입력 값이 올바른 형식을 따르지 않았습니다."),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "G-0008","입력 값이 형식 검사를 통과하지 못했습니다.."),

    DB_CONNECT_FAIL(HttpStatus.SERVICE_UNAVAILABLE, "G-0009" , "현재 서비스 상태가 고르지 못해 이용에 차질이 발생합니다. 운영자 이메일을 통해 상황을 제보 부탁드립니다."),

    FILE_SIZE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "G-0010", "업로드하신 파일의 크기가 너무 큽니다. 10MB 아래의 파일로 설정해주세요"),

    CURRENT_PAGE_BIGGER_LAST_PAGE(HttpStatus.BAD_REQUEST, "G-0011", "페이지 조회 시 현재 페이지가 마지막 페이지보다 높을 수 없습니다."),

    ARGUMENT_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "G-0012", "입력한 인자가 타입이 맞지 않습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
