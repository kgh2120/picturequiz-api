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

    LOGIN_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"U-0004","로그인 아이디가 잘못 되었습니다."),

    PASSWORD_INCORRECT(HttpStatus.CONFLICT, "U-0005", "입력하신 비밀번호가 틀렸습니다."),

    CHANGE_PASSWORD_SAME(HttpStatus.CONFLICT, "U-0006", "이전 비밀번호와 같은 비밀번호로 변경을 시도하였습니다."),

    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "U-0007", "이메일을 통해 인증에 실패했습니다."),

    INVALID_ACCESS_TO_CHANGE_TEMPORARY_PASSWORD(HttpStatus.NOT_FOUND, "U-0008", "임시 비밀번호 발급을 위한 인증에 실패했습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }

}
