package com.ssafy.nashda.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImgWordSetListResponseDto {
    private int type;
    private long index;
    private List<String> img;
    private String answer;

}
