package com.kk.picturequizapi.domain.report.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum ReportErrorCode implements ErrorCode {
    INPUT_NULL_DATA_ON_REPORT_CONTENT(HttpStatus.INTERNAL_SERVER_ERROR, "r-0001","신고 데이터가 생성될 때, 신고 내용에 null이 들어갔습니다."),
    INPUT_NULL_DATA_ON_TARGET_ID(HttpStatus.INTERNAL_SERVER_ERROR, "r-0002","신고 데이터가 생성될 때, 타켓 ID에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_REPORTER(HttpStatus.INTERNAL_SERVER_ERROR, "r-0003","신고 데이터가 생성될 때, 신고자에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_REPORT_ID(HttpStatus.INTERNAL_SERVER_ERROR, "r-0004","신고 데이터가 생성될 때, 신고ID에 null 이 들어갔습니다."),

    ALREADY_REPORTED_QUIZ(HttpStatus.BAD_REQUEST, "r-0005", "해당 사항으로 이미 신고가 접수되었습니다."),

    INPUT_NULL_DATA_ON_TARGET_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "r-0006","신고 데이터가 생성될 때, TARGET_TYPE에 null 이 들어갔습니다."),
    
    INVALID_TARGET_ID(HttpStatus.BAD_REQUEST, "r-0007", "사용할 수 없는 ID를 입력하셨습니다."),

    BOTH_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "r-0008", "신고 타입과 타켓 타입이 알맞지 않습니다."),


    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;


    @Override
    public String getErrorName() {
        return this.name();
    }
}
