package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_QUIZ_ID;

public class InputNullDataOnQuizIdException extends AbstractApiException {
    public InputNullDataOnQuizIdException() {
        super(INPUT_NULL_DATA_ON_QUIZ_ID);
    }
}
