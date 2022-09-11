package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.VERIFY_CODE_EXPIRED;

public class VerifyCodeExpiredException extends AbstractApiException {
    public VerifyCodeExpiredException() {
        super(VERIFY_CODE_EXPIRED);
    }
}
