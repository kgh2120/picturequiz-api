package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_QUIZ_ID;
import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_QUIZ_TAGS;

public class InputNullDataOnQuizTagsException extends AbstractApiException {
    public InputNullDataOnQuizTagsException() {
        super(INPUT_NULL_DATA_ON_QUIZ_TAGS);
    }
}
