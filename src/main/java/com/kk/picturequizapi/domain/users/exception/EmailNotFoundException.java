package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.EMAIL_NOT_FOUND;

public class EmailNotFoundException extends AbstractApiException {
    public EmailNotFoundException() {
        super(EMAIL_NOT_FOUND);
    }
}
