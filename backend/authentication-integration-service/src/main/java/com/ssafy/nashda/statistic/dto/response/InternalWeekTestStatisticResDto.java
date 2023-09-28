package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.WeekTestStatistic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalWeekTestStatisticResDto {
    //week와 score를 가지고 있음
    private long week;
    private int score;

    @Builder
    public InternalWeekTestStatisticResDto(WeekTestStatistic result) {
        this.week = result.getWeek().getWeekIdx();
        this.score = result.getMaxScore();
    }

}
