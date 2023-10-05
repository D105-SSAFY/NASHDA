package com.ssafy.nashda.game.entity;

import com.ssafy.nashda.game.dto.ImgWordHintDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 이미지 -단어 힌트 해당 Document
 * 2023-09-22
 * 조경호
 */
@Document(collection = "img_word_hint")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ImgWordHint {
    @Transient
    public static final String SEQUENCE_NAME = "img_word_hint";

    @Id
    private String id;

    private String word; // 단어
    private String type; // 품사
    private String description; // 설명

    public ImgWordHint(ImgWordHintDto imgWordHintDto) {
        this.word = imgWordHintDto.getWord();
        this.type = imgWordHintDto.getType();
        this.description = imgWordHintDto.getDescription();
    }
}
