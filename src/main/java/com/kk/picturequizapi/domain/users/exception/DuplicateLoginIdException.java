package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.DUPLICATE_LOGIN_ID;

public class DuplicateLoginIdException extends AbstractApiException {
    public DuplicateLoginIdException() {
        super(DUPLICATE_LOGIN_ID);
    }
}
