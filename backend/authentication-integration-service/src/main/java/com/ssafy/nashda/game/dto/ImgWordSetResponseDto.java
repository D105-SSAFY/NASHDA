package com.ssafy.nashda.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ImgWordSetResponseDto {

    private int type;
    private long index;
    private String img;
    private String answer;

}


//{
//        “status” : “200”,
//        “message” : “문제 불러오기 성공”,
//        “data” :
//        {
//        “type” : 1,
//        “index” : 2,
//        “img” : “src”,
//        “answer” : “사과”
//        }
//        }