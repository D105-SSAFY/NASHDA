package com.ssafy.nashda.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PronResponseDto {
    private String index;
    private String origin;
    private String convert;


}
