package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.statistic.entity.SimulStatistic;
import com.ssafy.nashda.statistic.entity.SimulType;

public interface SimulDetailService {

    Long createSimulDetail(SimulStatistic simulStatistic, String my_speech, String gpt_speech, SimulType type);
}
