package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INPUT_NULL_DATA_ON_REPORT_ID;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class InputNullDataOnReportId extends
        AbstractApiException {

    public InputNullDataOnReportId() {
        super(INPUT_NULL_DATA_ON_REPORT_ID);
    }
}
