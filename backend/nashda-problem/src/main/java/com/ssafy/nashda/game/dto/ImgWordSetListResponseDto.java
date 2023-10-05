package com.ssafy.nashda.game.dto;

import com.ssafy.nashda.game.entity.ImgWordSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImgWordSetListResponseDto {
    private int type;
    private long index;
    private List<String> img;
    private String answer;

    public ImgWordSetListResponseDto(List<ImgWordSet> imgWordSetList){
        this.img = new ArrayList<>();
        // 첫번째 리스트가 정답
        this.type = 2;
        this.index = imgWordSetList.get(0).getNum();

        for(ImgWordSet imgWordSet : imgWordSetList){
            this.img.add(imgWordSet.getImgURL());
        }

        this.answer = imgWordSetList.get(0).getWord();
    }
}
