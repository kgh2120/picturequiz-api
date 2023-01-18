package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.INPUT_NULL_DATA_ON_QUIZ_ID;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class InputNullDataOnQuizId extends
        AbstractApiException {

    public InputNullDataOnQuizId() {
        super(INPUT_NULL_DATA_ON_QUIZ_ID);
    }
}
