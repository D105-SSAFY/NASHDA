package com.ssafy.nashda.test.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "mix_test_result")
@Getter
@Builder
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MixTestResult {
    @Transient
    public static final String SEQUENCE_NAME = "mix_test_result";

    @Id
    private String id;

    @Field("member_number")
    private long memberNumber;

    private long week;
    private int score;

    @Field("try_count")
    private int tryCount;

    @Field("blank_test")
    private List<BlankTest> blankTest;

    @Field("speed_test1")
    private List<SpeedTest1> speedTest1;

    @Field("speed_test2")
    private List<SpeedTest2> speedTest2;



    @Builder
    public MixTestResult(long memberNumber, long week, int tryCount, List<BlankTest> blankTest, List<SpeedTest1> speedTest1, List<SpeedTest2> speedTest2) {
        this.memberNumber = memberNumber;
        this.week = week;
        this.tryCount = tryCount;
        this.blankTest = blankTest;
        this.speedTest1 = speedTest1;
        this.speedTest2 = speedTest2;
        this.score = -1;
    }

}
