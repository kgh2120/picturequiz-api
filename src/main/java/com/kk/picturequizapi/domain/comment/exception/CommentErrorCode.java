package com.kk.picturequizapi.domain.comment.exception;

import com.kk.picturequizapi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    QUIZ_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "c-0001", "입력하신 퀴즈 ID는 존재하지 않습니다."),
    PARENT_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "c-0002", "입력하신 부모 댓글 ID는 존재하지 않습니다."),

    COMMENT_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "c-0003", "입력하신 댓글 ID는 존재하지 않습니다."),

    COMMENT_NOT_YOURS(HttpStatus.FORBIDDEN, "c-0004", "댓글에 접근할 권한이 없습니다."),

    ACCESS_DELETED_COMMENT(HttpStatus.BAD_REQUEST, "c-0005", "삭제된 댓글입니다.")

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;


    @Override
    public String getErrorName() {
        return this.name();
    }
}
