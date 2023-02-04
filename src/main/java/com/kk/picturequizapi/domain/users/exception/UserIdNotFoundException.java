package com.kk.picturequizapi.domain.users.exception;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.USER_ID_NOT_FOUND;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class UserIdNotFoundException extends AbstractApiException {

    public UserIdNotFoundException() {
        super(USER_ID_NOT_FOUND);
    }
}
