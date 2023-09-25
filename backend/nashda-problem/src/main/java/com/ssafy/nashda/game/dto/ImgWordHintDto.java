package com.ssafy.nashda.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImgWordHintDto {
    private String word;

    private String type; // 품사

    private String description; // 설명
}
