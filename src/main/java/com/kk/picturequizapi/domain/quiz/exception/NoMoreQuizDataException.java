package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.NO_MORE_QUIZ_DATA;

public class NoMoreQuizDataException extends AbstractApiException {
    public NoMoreQuizDataException() {
        super(NO_MORE_QUIZ_DATA);
    }
}
