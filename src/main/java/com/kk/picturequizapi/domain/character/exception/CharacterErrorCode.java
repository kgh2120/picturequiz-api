package com.kk.picturequizapi.domain.character.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum CharacterErrorCode implements ErrorCode {

    CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "c-0001", "캐릭터를 찾을 수 없습니다.")

    ;


    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;


    @Override
    public String getErrorName() {
        return this.name();
    }


}
