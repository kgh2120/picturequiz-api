package com.kk.picturequizapi.domain.quiz.exception;

import com.kk.picturequizapi.global.exception.AbstractApiException;

import static com.kk.picturequizapi.domain.quiz.exception.QuizErrorCode.FILE_TYPE_NOT_IMAGE;

public class FileTypeNotImageFileException extends AbstractApiException {
    public FileTypeNotImageFileException() {
        super(FILE_TYPE_NOT_IMAGE);
    }
}
