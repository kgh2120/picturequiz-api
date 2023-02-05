package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.INVALID_ACCESS_TO_CHANGE_TEMPORARY_PASSWORD;

public class InvalidAccessToChangeTemporaryPasswordException extends AbstractApiException {
    public InvalidAccessToChangeTemporaryPasswordException() {
        super(INVALID_ACCESS_TO_CHANGE_TEMPORARY_PASSWORD);
    }
}
