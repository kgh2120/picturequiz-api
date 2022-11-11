package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.PASSWORD_INCORRECT;

public class PasswordIncorrectException extends AbstractApiException {
    public PasswordIncorrectException() {
        super(PASSWORD_INCORRECT);
    }
}
