package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INPUT_NULL_DATA_ON_REPORT_CONTENT;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class InputNullDataOnReportContent extends
        AbstractApiException {

    public InputNullDataOnReportContent() {
        super(INPUT_NULL_DATA_ON_REPORT_CONTENT);
    }
}
