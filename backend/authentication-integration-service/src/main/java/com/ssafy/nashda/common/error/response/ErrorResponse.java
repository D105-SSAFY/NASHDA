package com.ssafy.nashda.common.error.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String httpStatus;
    private int errorCode;
    private String errorMessage;

    public static ErrorResponse of(String httpStatus, int errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}