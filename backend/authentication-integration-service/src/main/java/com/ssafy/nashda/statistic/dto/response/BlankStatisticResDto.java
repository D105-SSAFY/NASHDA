package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.week.entity.Week;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlankStatisticResDto {
    private LocalDateTime start_date;
    private LocalDateTime end_data;
    private int score;
    private int total;
    private int set;

    @Builder
    public BlankStatisticResDto(Week week, int score, int total, int set) {
        this.start_date = week.getStartDate();
        this.end_data = week.getEndDate();
        this.score = score;
        this.total = total;
        this.set = set;
    }
}
