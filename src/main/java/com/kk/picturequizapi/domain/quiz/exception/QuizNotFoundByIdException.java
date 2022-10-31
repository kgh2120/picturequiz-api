package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.QUIZ_NOT_FOUND_BY_ID;

public class QuizNotFoundByIdException extends AbstractApiException {
    public QuizNotFoundByIdException( ) {
        super(QUIZ_NOT_FOUND_BY_ID);
    }
}
