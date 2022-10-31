package com.kk.picturequizapi.domain.character.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.character.exception.CharacterErrorCode.CHARACTER_NOT_FOUND;

public class CharacterNotFoundException extends AbstractApiException {
    public CharacterNotFoundException() {
        super(CHARACTER_NOT_FOUND);
    }
}
