package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.BOTH_TYPE_MISMATCH;

import com.kk.picturequizapi.global.exception.AbstractApiException;

public class BothTypeMismatchException extends AbstractApiException {

    public BothTypeMismatchException() {
        super(BOTH_TYPE_MISMATCH);
    }
}
