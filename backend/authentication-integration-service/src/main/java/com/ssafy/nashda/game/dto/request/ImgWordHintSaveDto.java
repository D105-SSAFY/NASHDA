package com.ssafy.nashda.game.dto.request;


import lombok.*;

/**
 * 이미지 -단어 힌트
 * 2023-09-22
 * 조경호
 */

@Getter @Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ImgWordHintSaveDto {
    private String word; // 단어
    private String type; // 품사
    private String description; // 설명

}
