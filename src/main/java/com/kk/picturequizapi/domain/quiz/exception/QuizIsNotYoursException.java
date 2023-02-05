package com.kk.picturequizapi.domain.quiz.exception;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.QUIZ_IS_NOT_YOURS;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class QuizIsNotYoursException extends AbstractApiException {

    public QuizIsNotYoursException( ) {
        super(QUIZ_IS_NOT_YOURS);
    }
}
