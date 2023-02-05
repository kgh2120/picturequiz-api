package com.kk.picturequizapi.global.exception;

import static com.kk.picturequizapi.global.exception.GlobalErrorCode.CURRENT_PAGE_BIGGER_LAST_PAGE;

public class CurrentPageBiggerLastPageException extends AbstractApiException{

    public CurrentPageBiggerLastPageException() {
        super(CURRENT_PAGE_BIGGER_LAST_PAGE);
    }
}
