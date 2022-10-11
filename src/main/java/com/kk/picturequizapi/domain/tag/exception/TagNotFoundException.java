package com.kk.picturequizapi.domain.tag.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.tag.exception.TagErrorCode.TAG_NOT_FOUND;

public class TagNotFoundException extends AbstractApiException {
    public TagNotFoundException() {
        super(TAG_NOT_FOUND);
    }
}
