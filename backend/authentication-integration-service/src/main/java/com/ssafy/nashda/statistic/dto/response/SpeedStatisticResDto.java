package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.GameStatistic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SpeedStatisticResDto {
    private LocalDateTime start_date;
    private LocalDateTime end_data;
    private int score;
    private int total;
    private int set;

    @Builder
    public SpeedStatisticResDto(GameStatistic gameStatistic) {
        this.start_date = gameStatistic.getWeek().getStartDate();
        this.end_data = gameStatistic.getWeek().getEndDate();
        this.score = gameStatistic.getBlankScore();
        this.total = gameStatistic.getBlankTotal();
        this.set = gameStatistic.getBlankSet();
    }
}
