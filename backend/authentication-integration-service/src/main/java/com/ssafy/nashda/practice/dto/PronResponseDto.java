package com.ssafy.nashda.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PronResponseDto {
    private String index;
    private String origin;
    private String convert;
    private List<String> imgList;

}
