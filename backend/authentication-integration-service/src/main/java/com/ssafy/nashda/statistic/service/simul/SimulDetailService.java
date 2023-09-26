package com.ssafy.nashda.statistic.service.simul;

import com.ssafy.nashda.statistic.entity.simul.SimulStatic;

public interface SimulDetailService {

    Long createSimulDetail(SimulStatic simulStatic, String my_speech, String gpt_speech, String type);
}
