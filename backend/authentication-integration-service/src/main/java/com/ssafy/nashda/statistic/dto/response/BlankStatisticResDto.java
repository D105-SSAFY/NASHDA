package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.week.entity.Week;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlankStatisticResDto {
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int score;
    private int total;
    private int set;

    @Builder
    public BlankStatisticResDto(GameStatistic gameStatistic) {
        this.start_date = gameStatistic.getWeek().getStartDate();
        this.end_date = gameStatistic.getWeek().getEndDate();
        this.score = gameStatistic.getBlankScore();
        this.total = gameStatistic.getBlankTotal();
        this.set = gameStatistic.getBlankSet();
    }
}
