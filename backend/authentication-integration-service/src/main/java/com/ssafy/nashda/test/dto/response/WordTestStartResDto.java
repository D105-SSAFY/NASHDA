package com.ssafy.nashda.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WordTestStartResDto {
    private int try_count;
    private String index;   //mongodb의 index
    private List<String> problem;
    private List<String> convert;
}
