package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INVALID_TARGET_ID;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class InvalidTargetIdException extends AbstractApiException {

    public InvalidTargetIdException() {
        super(INVALID_TARGET_ID);
    }
}
