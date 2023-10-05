package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.GameStatistic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BlankStatisticResDto {
    private LocalDate start_date;
    private LocalDate end_date;
    private int score;
    private int total;
    private int set;

    @Builder
    public BlankStatisticResDto(GameStatistic gameStatistic) {
        this.start_date = gameStatistic.getWeek().getStartDate().toLocalDate();
        this.end_date = gameStatistic.getWeek().getEndDate().toLocalDate();
        this.score = gameStatistic.getBlankScore();
        this.total = gameStatistic.getBlankTotal();
        this.set = gameStatistic.getBlankSet();
    }
}
