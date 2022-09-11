package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.VERIFY_CODE_INVALID;

public class VerifyCodeInvalidException extends AbstractApiException {
    public VerifyCodeInvalidException() {
        super(VERIFY_CODE_INVALID);
    }
}
