package com.kk.picturequizapi.domain.users.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.CHANGE_PASSWORD_SAME;

public class ChangePasswordSameException extends AbstractApiException {
    public ChangePasswordSameException() {
        super(CHANGE_PASSWORD_SAME);
    }
}
