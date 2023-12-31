package com.ssafy.nashda.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //  null 값을 가지는 필드는, JSON 응답에 포함되지 않음
public class BaseResponseBody<T>  {
    private int status;
    private String message;
    private T data;

    public BaseResponseBody(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
