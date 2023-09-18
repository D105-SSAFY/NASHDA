package com.ssafy.nashda.common.error.exception;

import com.ssafy.nashda.common.error.code.ErrorCode;

public class BadRequestException extends RuntimeException {

    private ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
