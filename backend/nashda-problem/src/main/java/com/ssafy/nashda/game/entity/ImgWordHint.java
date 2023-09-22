package com.ssafy.nashda.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 이미지 -단어 힌트 해당 Document
 * 2023-09-22
 * 조경호
 * */
@Document(collection = "img_word_hint")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ImgWordHint {
    @Id
    private String id;

    private String type;
    private String description;
}
