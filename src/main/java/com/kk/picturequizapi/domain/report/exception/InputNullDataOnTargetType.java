package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INPUT_NULL_DATA_ON_TARGET_TYPE;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class InputNullDataOnTargetType extends AbstractApiException {

    public InputNullDataOnTargetType() {
        super(INPUT_NULL_DATA_ON_TARGET_TYPE);
    }
}
