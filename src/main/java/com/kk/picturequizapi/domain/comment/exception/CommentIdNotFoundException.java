package com.kk.picturequizapi.domain.comment.exception;

import static com.kk.picturequizapi.domain.comment.exception.CommentErrorCode.COMMENT_ID_NOT_FOUND;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class CommentIdNotFoundException extends AbstractApiException {

    public CommentIdNotFoundException() {
        super(COMMENT_ID_NOT_FOUND);
    }
}
