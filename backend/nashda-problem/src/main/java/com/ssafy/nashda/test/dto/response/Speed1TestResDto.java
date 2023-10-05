package com.ssafy.nashda.test.dto.response;

import com.ssafy.nashda.game.entity.ImgWordSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Speed1TestResDto {

    private String img;
    private String answer;


    public Speed1TestResDto(ImgWordSet imgWordSet){;
        this.img = imgWordSet.getImgURL();
        this.answer = imgWordSet.getWord();
    }
}