package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.LOGIN_ID_NOT_FOUND;

public class LogInIdNotFoundException extends AbstractApiException {
    public LogInIdNotFoundException() {
        super(LOGIN_ID_NOT_FOUND);
    }
}
