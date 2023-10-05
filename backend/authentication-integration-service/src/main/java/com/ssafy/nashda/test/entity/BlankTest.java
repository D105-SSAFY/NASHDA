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
public class BlankTest {
    String imgURL;
    String answer;  //전체 문장
    List<String> word;  //뚫은 빈칸들
    List<String> hint;  //힌트들

    //사용자 발음 stt
    @Field("user_stt")
    String userAnswer;

    @Field("sound_url")
    String soundUrl;

}
