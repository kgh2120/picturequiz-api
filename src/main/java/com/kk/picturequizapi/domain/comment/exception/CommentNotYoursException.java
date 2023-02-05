package com.kk.picturequizapi.domain.comment.exception;

import static com.kk.picturequizapi.domain.comment.exception.CommentErrorCode.COMMENT_NOT_YOURS;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class CommentNotYoursException extends AbstractApiException {

    public CommentNotYoursException( ) {
        super(COMMENT_NOT_YOURS);
    }
}
