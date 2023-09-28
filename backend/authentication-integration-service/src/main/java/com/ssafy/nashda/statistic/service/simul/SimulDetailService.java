package com.ssafy.nashda.statistic.service.simul;

import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import com.ssafy.nashda.statistic.entity.simul.SimulType;

public interface SimulDetailService {

    Long createSimulDetail(SimulStatistic simulStatistic, String my_speech, String gpt_speech, SimulType type);
}
