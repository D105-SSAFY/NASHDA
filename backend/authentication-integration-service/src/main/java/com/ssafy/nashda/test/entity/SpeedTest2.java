package com.ssafy.nashda.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SpeedTest2 {
    private int index;
    private List<String> imgUrl;    //항상 0번째가 정답이다.
    private String answer;
}
