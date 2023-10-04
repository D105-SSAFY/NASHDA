package com.ssafy.nashda.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PronSTTResponseDto {
    private String stt;
    List<PronImgDto> pronImgDtoList;
}
