package com.kk.picturequizapi.domain.report.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum ReportErrorCode implements ErrorCode {
    INPUT_NULL_DATA_ON_REPORT_CONTENT(HttpStatus.INTERNAL_SERVER_ERROR, "r-0001","신고 데이터가 생성될 때, 신고 내용에 null이 들어갔습니다."),
    INPUT_NULL_DATA_ON_QUIZ_ID(HttpStatus.INTERNAL_SERVER_ERROR, "r-0002","신고 데이터가 생성될 때, 퀴즈 ID에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_REPORTER(HttpStatus.INTERNAL_SERVER_ERROR, "r-0003","신고 데이터가 생성될 때, 신고자에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_REPORT_ID(HttpStatus.INTERNAL_SERVER_ERROR, "r-0004","신고 데이터가 생성될 때, 신고ID에 null 이 들어갔습니다."),
    
    


    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;


    @Override
    public String getErrorName() {
        return this.name();
    }
}
