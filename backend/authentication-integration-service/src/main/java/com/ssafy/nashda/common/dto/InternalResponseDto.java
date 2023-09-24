package com.ssafy.nashda.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InternalResponseDto<T> {
    String status;
    String message;

    T data;
}
