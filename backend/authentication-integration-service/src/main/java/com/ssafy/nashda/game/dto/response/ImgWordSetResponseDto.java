package com.ssafy.nashda.game.dto.response;

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