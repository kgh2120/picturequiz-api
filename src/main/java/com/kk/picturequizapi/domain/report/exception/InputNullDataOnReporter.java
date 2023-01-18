package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INPUT_NULL_DATA_ON_REPORTER;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class InputNullDataOnReporter extends
        AbstractApiException {

    public InputNullDataOnReporter() {
        super(INPUT_NULL_DATA_ON_REPORTER);
    }
}
