package com.ssafy.nashda.test.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "word_test_result")
@Getter
@Builder
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class WordTestResult {

    @Transient
    public static final String SEQUENCE_NAME = "word_test_result";

    @Id
    private String id;

    @Field("member_number")
    private long memberNumber;
    private long week;

    private int score;

    private List<String> question;
    private List<String> pronunciation;

    @Field("user_pronunciation")
    private List<String> userPron;

    @Builder
    public WordTestResult(long memberNumber, long week) {
        this.memberNumber = memberNumber;
        this.week = week;
    }



}
