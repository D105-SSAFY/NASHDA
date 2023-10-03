package com.ssafy.nashda.test.dto.response;

import com.ssafy.nashda.test.dto.request.WeekTestResultDetailReqDto;
import com.ssafy.nashda.test.entity.BlankTest;
import com.ssafy.nashda.test.entity.MixTestResult;
import com.ssafy.nashda.test.entity.SpeedTest1;
import com.ssafy.nashda.test.entity.SpeedTest2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WeekTestResultDetailResDto {
    private int score;
    private List<BlankTest> blankTest;
    private List<SpeedTest1> speedTest1;
    private List<SpeedTest2> speedTest2;

    @Builder
    public WeekTestResultDetailResDto(MixTestResult mixTestResult){
        this.score = mixTestResult.getScore();
        this.blankTest = mixTestResult.getBlankTest();
        this.speedTest1 = mixTestResult.getSpeedTest1();
        this.speedTest2 = mixTestResult.getSpeedTest2();
    }
}
