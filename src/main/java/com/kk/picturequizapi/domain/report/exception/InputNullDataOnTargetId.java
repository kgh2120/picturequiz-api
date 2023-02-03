package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INPUT_NULL_DATA_ON_TARGET_ID;

import com.kk.picturequizapi.global.exception.AbstractApiException;

public class InputNullDataOnTargetId extends
        AbstractApiException {

    public InputNullDataOnTargetId() {
        super(INPUT_NULL_DATA_ON_TARGET_ID);
    }
}
