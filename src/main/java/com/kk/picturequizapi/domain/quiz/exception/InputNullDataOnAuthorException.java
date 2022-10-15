package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_AUTHOR;
import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_QUIZ_ID;

public class InputNullDataOnAuthorException extends AbstractApiException {
    public InputNullDataOnAuthorException() {
        super(INPUT_NULL_DATA_ON_AUTHOR);
    }
}
