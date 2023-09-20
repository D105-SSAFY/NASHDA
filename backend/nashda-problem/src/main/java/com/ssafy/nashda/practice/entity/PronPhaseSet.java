package com.ssafy.nashda.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 발음 - 구 문제 해당 Document
 * 2023-09-19
 * 조경호
 * */
@Document(collection = "pronunciation_phase_set")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PronPhaseSet {
    @Transient
    public static final String SEQUENCE_NAME = "pronunciation_phase_sequence";

    @Id
    private String id;

    private long num;
    private String originSentence;
    private String pronunciation;
    private String domain;
}
