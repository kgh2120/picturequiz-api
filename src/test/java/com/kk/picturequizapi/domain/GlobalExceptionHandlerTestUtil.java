package com.kk.picturequizapi.domain;

import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.global.exception.AbstractApiException;
import com.kk.picturequizapi.global.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTestUtil {

    public static ResponseEntity<ErrorResponse> createErrorResponse(AbstractApiException ex, String path) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(ex
                        ,path));
    }
}
