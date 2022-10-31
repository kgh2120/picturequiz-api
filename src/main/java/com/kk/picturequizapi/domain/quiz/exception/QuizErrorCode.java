package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter @RequiredArgsConstructor
public enum QuizErrorCode implements ErrorCode {


    FILE_TYPE_NOT_IMAGE(HttpStatus.BAD_REQUEST,"q-0001","전송한 파일이 이미지 타입이 아닙니다."),
    INPUT_NULL_DATA_ON_QUIZ_ID(HttpStatus.INTERNAL_SERVER_ERROR, "q-0002","퀴즈 데이터가 생성될 때, 퀴즈 ID에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_AUTHOR(HttpStatus.INTERNAL_SERVER_ERROR, "q-0003","퀴즈 데이터가 생성될 때, 제작자에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_ANSWER(HttpStatus.INTERNAL_SERVER_ERROR, "q-0004","퀴즈 데이터가 생성될 때, 정답에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_QUIZ_TAGS(HttpStatus.INTERNAL_SERVER_ERROR, "q-0005","퀴즈 데이터가 생성될 때, 퀴즈 태그에 null 이 들어갔습니다."),
    INPUT_NULL_DATA_ON_PICTURE(HttpStatus.INTERNAL_SERVER_ERROR, "q-0006","퀴즈 데이터가 생성될 때, 사진에 null 이 들어갔습니다."),

    NO_MORE_QUIZ_DATA(HttpStatus.NOT_FOUND,"q-0007","더이상 조회할 수 있는 퀴즈가 존재하지 않습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;


    @Override
    public String getErrorName() {
        return this.name();
    }
}
