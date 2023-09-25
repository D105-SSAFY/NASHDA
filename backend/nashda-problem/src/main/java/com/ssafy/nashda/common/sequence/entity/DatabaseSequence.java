package com.ssafy.nashda.common.sequence.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 2023-09-18
 * MongoDB에 Auto Increment를 위해 테이블에 Sequence 저장
 * 조경호
 * */

@Document(collection = "database_sequences")
@Getter
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;
}
