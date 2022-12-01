package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.NO_SEARCH_RESULT;

public class NoSearchResultException extends AbstractApiException {
    public NoSearchResultException() {
        super(NO_SEARCH_RESULT);
    }
}
