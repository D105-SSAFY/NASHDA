package com.ssafy.nashda.game.dto;

import com.ssafy.nashda.game.entity.ImgWordHint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ImgWordSetSaveRequestDto {

    private String imgUrl;

    private ImgWordHint imgWordHint;
}
