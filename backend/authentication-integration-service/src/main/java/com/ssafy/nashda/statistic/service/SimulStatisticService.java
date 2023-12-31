package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.SimulStatistic;
import com.ssafy.nashda.statistic.entity.SimulType;

public interface SimulStatisticService {
    SimulStatistic createSimulStatic(Member member, SimulType simulType);

}
