package com.ssafy.nashda.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
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
public class ImgWordSet {
    @Id
    private String id;

    private String imgURL;
    private String word;

    @DocumentReference
    private ImgWordHint imgWordHint;
}
