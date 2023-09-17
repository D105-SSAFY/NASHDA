package com.ssafy.nashda.exception;

import io.netty.handler.codec.string.StringDecoder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {
    USER_EXIST(HttpStatus.BAD_REQUEST, 4000, "회원이 존재합니다."),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, 4001, "회원이 존재하지 않습니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, 4004, "아이디 혹은 비밀번호를 확인하세요.");

    private HttpStatus status;
    private int code;
    private String description;

    private ExceptionEnum(HttpStatus status, int code, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    }
