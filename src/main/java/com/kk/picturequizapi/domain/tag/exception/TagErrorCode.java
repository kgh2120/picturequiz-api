package com.kk.picturequizapi.domain.tag.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum TagErrorCode implements ErrorCode {

    // 검색한 태그가 존재하지 않은 경우
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND,"t-0001","태그가 존재하지 않습니다."),
    TAG_NAME_DUPLICATE(HttpStatus.CONFLICT, "t-0002","해당 이름은 이미 존재합니다.")


    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
