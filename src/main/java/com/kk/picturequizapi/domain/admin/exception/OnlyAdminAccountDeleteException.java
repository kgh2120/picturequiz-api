package com.kk.picturequizapi.domain.admin.exception;

import static com.kk.picturequizapi.domain.admin.exception.AdminErrorCode.ONLY_ADMIN_ACCOUNT_DELETE;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class OnlyAdminAccountDeleteException extends AbstractApiException {

    public OnlyAdminAccountDeleteException() {
        super(ONLY_ADMIN_ACCOUNT_DELETE);
    }
}
