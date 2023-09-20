package com.ssafy.nashda.common.error.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    TEST(HttpStatus.INTERNAL_SERVER_ERROR, 001, "exception test"),
    NOT_EXISTS_DATA(HttpStatus.BAD_REQUEST, 4000, "존재하지 않는 데이터입니다."),
    USER_EXIST(HttpStatus.BAD_REQUEST, 4000, "회원이 존재합니다."),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, 4001, "회원이 존재하지 않습니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, 4004, "아이디 혹은 비밀번호를 확인하세요."),
    INVALID_USER(HttpStatus.BAD_REQUEST,4003,"올바르지 않은 사용자의 접근입니다.");


    private HttpStatus httpStatus;
    private int errorCode;
    private String message;

    ErrorCode(HttpStatus httpStatus, int errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
