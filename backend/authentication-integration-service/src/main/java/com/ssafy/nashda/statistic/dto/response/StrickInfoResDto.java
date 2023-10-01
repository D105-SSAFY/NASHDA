package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.Strick;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//전체 스트릭 정보
@Getter
@Setter
public class StrickInfoResDto {
    private LocalDate date;
    private int blank_count;
    private int speed_count;
    private int practice_count;
    private int conversation_count;
    private int test_count;
    private int total_count;
    @Builder
    public StrickInfoResDto(Strick strick) {
        this.date = strick.getCreatOn();
        this.blank_count = strick.getBlankCount();
        this.speed_count = strick.getSpeedCount();
        this.practice_count = strick.getPracticeCount();
        this.conversation_count = strick.getConversationCount();
        this.test_count = strick.getTestCount();
        this.total_count = this.blank_count+this.speed_count+this.practice_count+this.conversation_count+this.test_count;
    }

}
