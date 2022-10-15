package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_ANSWER;
import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.INPUT_NULL_DATA_ON_QUIZ_ID;

public class InputNullDataOnAnswerException extends AbstractApiException {
    public InputNullDataOnAnswerException() {
        super(INPUT_NULL_DATA_ON_ANSWER);
    }
}
