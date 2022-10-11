package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter @RequiredArgsConstructor
public enum QuizErrorCode implements ErrorCode {


    FILE_TYPE_NOT_IMAGE(HttpStatus.BAD_REQUEST,"q-0001","전송한 파일이 이미지 타입이 아닙니다.")

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;


    @Override
    public String getErrorName() {
        return this.name();
    }
}
