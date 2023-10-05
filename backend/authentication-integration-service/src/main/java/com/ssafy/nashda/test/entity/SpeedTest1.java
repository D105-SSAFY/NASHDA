package com.ssafy.nashda.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/*
    그림 보고 단어 맞추기~!
 */
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SpeedTest1 {
    private String img;    //항상 0번째가 정답이다.
    private String answer;

    @Field("user_stt")
    private String userSTT; //사용자의 말 stt;

    @Field("sound_url")
    private String soundUrl;

}
