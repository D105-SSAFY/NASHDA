package com.ssafy.nashda.statistic.service.simul;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.simul.SimulType;

public interface SimulStatisticService {
    void createSimulStatic(Member member, SimulType simulType);

}
