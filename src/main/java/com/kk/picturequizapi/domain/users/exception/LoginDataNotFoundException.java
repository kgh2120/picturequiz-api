package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.LOGIN_DATA_NOT_FOUND;

public class LoginDataNotFoundException extends AbstractApiException {
    public LoginDataNotFoundException() {
        super(LOGIN_DATA_NOT_FOUND);
    }
}
