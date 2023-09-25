package com.ssafy.nashda.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "blank_question_set")
public class BlankQuestionSet {
    @Transient
    public static final String SEQUENCE_NAME = "blank_question_set";

    @Id
    private String id;

    private long num;
    private String imgURL;

    private String correctAnswer;

    @DBRef
    private ImgWordHint word1;
    @DBRef
    private ImgWordHint word2;
    @DBRef
    private ImgWordHint word3;


}
