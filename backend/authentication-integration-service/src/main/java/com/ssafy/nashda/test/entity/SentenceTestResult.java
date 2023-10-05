package com.ssafy.nashda.test.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "sentence_test_result")
@Getter
@Builder
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SentenceTestResult {

    @Transient
    public static final String SEQUENCE_NAME = "sentence_test_result";

    @Id
    private String id;

    @Field("member_number")
    private long memberNumber;
    private long week;

    private int score;

    private List<String> question = new ArrayList<>();
    private List<String> pronunciation = new ArrayList<>();

    @Field("user_pronunciation")
    private List<String> userPron;  //사용자 음성 파일 url 저장

    @Builder
    public SentenceTestResult(long memberNumber, long week, List<String> question, List<String> pronunciation) {
        this.memberNumber = memberNumber;
        this.week = week;
        this.question = question;
        this.pronunciation = pronunciation;
    }


}
