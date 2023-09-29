package com.ssafy.nashda.game.dto.request;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalBlankSetSaveReqDto {
    private String imgUrl;
    private String correctAnswer;

    private ImgWordHintSaveDto word1;
    private ImgWordHintSaveDto word2;
    private ImgWordHintSaveDto word3;
}
