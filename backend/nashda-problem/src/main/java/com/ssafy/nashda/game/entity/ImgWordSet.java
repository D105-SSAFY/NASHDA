package com.ssafy.nashda.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * 이미지 -단어 문제 해당 Document
 * 2023-09-22
 * 조경호
 * */
@Document(collection = "img_word_set")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ImgWordSet {
    @Transient
    public static final String SEQUENCE_NAME = "img_word_set";

    @Id
    private String id;

    private long num;
    private String imgURL; // 이미지 경로
    private String word; // 단어

    @DBRef
    private ImgWordHint imgWordHint;
}
