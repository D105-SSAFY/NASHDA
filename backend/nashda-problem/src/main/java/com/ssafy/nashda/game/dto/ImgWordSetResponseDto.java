package com.ssafy.nashda.game.dto;

import com.ssafy.nashda.game.entity.ImgWordSet;
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


    public ImgWordSetResponseDto(ImgWordSet imgWordSet){
        this.index = imgWordSet.getNum();
        this.img = imgWordSet.getImgURL();
        this.answer = imgWordSet.getWord();
    }
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