package com.ssafy.nashda.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SpeedTest2 {
    private int index;
    private List<String> img;    //항상 0번째가 정답이다.
    private String answer;
    @Field("user_answer")
    String userAnswer;

}
