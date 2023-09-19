package com.ssafy.nashda.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}