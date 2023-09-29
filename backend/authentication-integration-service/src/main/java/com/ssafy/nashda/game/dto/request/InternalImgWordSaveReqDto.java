package com.ssafy.nashda.game.dto.request;


import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternalImgWordSaveReqDto {
    private String imgUrl;
    private ImgWordHintSaveDto imgWordHint;

}
