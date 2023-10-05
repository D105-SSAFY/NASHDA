package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.GameStatistic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SpeedStatisticResDto {
    private LocalDate start_date;
    private LocalDate end_data;
    private int score;
    private int total;
    private int set;

    @Builder
    public SpeedStatisticResDto(GameStatistic gameStatistic) {
        this.start_date = gameStatistic.getWeek().getStartDate().toLocalDate();
        this.end_data = gameStatistic.getWeek().getEndDate().toLocalDate();
        this.score = gameStatistic.getSpeedScore();
        this.total = gameStatistic.getSpeedTotal();
        this.set = gameStatistic.getSpeedSet();
    }
}
