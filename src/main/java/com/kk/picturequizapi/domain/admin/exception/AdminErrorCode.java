package com.kk.picturequizapi.domain.admin.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum AdminErrorCode implements ErrorCode {

    ONLY_ADMIN_ACCOUNT_DELETE(HttpStatus.BAD_REQUEST,"A-0001", "관리자 계정만 삭제가 가능합니다."),
    ;
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }

}
