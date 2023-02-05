package com.kk.picturequizapi.domain.users.exception;

import static com.kk.picturequizapi.domain.users.exception.UserErrorCode.BLOCK_USER_LOGIN;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class BlockUserLoginException extends AbstractApiException {

    public BlockUserLoginException(LocalDate blockedDate) {
        super(BLOCK_USER_LOGIN);
        super.errorMessage = super.getErrorMessage() + blockedDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }
}
