package com.kk.picturequizapi.global.exception;

import static com.kk.picturequizapi.global.exception.GlobalErrorCode.BIND_ERROR;

public class LoginValidateErrorException extends AbstractApiException{
    public LoginValidateErrorException() {
        super(BIND_ERROR);
    }
}
