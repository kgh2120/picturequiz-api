package com.kk.picturequizapi.domain.comment.exception;

import static com.kk.picturequizapi.domain.comment.exception.CommentErrorCode.PARENT_ID_NOT_FOUND;

import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorCode;

public class ParentIdNotFoundException extends AbstractApiException {

    public ParentIdNotFoundException() {
        super(PARENT_ID_NOT_FOUND);
    }
}
