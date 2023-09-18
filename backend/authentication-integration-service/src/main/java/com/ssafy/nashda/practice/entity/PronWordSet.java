package com.ssafy.nashda.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;

@Document(collection = "pronunciation_word_set")
@Builder
@AllArgsConstructor
@Data
public class PronWordSet {
    @Transient
    public static final String SEQUENCE_NAME = "pronunciation_sequence";

    @Id
    private String id;

    private long num;
    private String originSentence;
    private String pronunciation;
    private String domain;
}
