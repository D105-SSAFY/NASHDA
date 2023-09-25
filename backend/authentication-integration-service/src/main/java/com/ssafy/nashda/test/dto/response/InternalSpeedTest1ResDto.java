package com.ssafy.nashda.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class InternalSpeedTest1ResDto {
    private String imgUrl;
    private String answer;
}
