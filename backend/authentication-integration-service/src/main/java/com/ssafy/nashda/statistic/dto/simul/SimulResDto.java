package com.ssafy.nashda.statistic.dto.simul;

import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import com.ssafy.nashda.statistic.entity.simul.SimulType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimulResDto {

    private String background;
    private Long score;
    private Long total;

    public SimulResDto(SimulStatistic simulStatistic, SimulType simulType) {
        this.background = simulType.getName();
        this.score = simulStatistic.getCorrect();
        this.total = simulStatistic.getTotal();
    }
}
