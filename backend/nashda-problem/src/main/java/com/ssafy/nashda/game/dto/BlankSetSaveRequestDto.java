package com.ssafy.nashda.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlankSetSaveRequestDto {
    private String imgUrl;
    private String correctAnswer;

    private ImgWordHintDto word1;
    private ImgWordHintDto word2;
    private ImgWordHintDto word3;
}
