package com.kk.picturequizapi.domain.tag.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.tag.exception.TagErrorCode.TAG_NAME_DUPLICATE;

public class TagNameDuplicateException extends AbstractApiException {
    public TagNameDuplicateException() {
        super(TAG_NAME_DUPLICATE);
    }
}
