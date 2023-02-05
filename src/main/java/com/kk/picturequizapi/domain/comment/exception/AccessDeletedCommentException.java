package com.kk.picturequizapi.domain.comment.exception;

import static com.kk.picturequizapi.domain.comment.exception.CommentErrorCode.ACCESS_DELETED_COMMENT;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class AccessDeletedCommentException extends AbstractApiException {

    public AccessDeletedCommentException( ) {
        super(ACCESS_DELETED_COMMENT);
    }
}
