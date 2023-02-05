package com.kk.picturequizapi.domain.report.exception;

import static com.kk.picturequizapi.domain.report.exception.ReportErrorCode.ALREADY_REPORTED_QUIZ;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class AlreadyReportedQuizException extends AbstractApiException {

    public AlreadyReportedQuizException( ) {
        super(ALREADY_REPORTED_QUIZ);
    }
}
