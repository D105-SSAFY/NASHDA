package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.GameStatistic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatisticResDto {
    Long week;
    int blank_score;
    int blank_total;
    int blank_set;
    int speed_score;
    int speed_total;
    int speed_set;

    @Builder
    public GameStatisticResDto(GameStatistic gameStatistic){
        this.week = gameStatistic.getWeek().getWeekIdx();
        this.blank_score = gameStatistic.getBlankScore();
        this.blank_total = gameStatistic.getBlankTotal();
        this.blank_set = gameStatistic.getBlankSet();
        this.speed_score = gameStatistic.getSpeedScore();
        this.speed_total = gameStatistic.getSpeedTotal();
        this.speed_set = gameStatistic.getSpeedSet();

    }
}
