package com.kk.picturequizapi.domain.comment.exception;

import static com.kk.picturequizapi.domain.comment.exception.CommentErrorCode.QUIZ_ID_NOT_FOUND;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class QuizIdNotFoundException extends AbstractApiException {

    public QuizIdNotFoundException() {
        super(QUIZ_ID_NOT_FOUND);
    }
}
