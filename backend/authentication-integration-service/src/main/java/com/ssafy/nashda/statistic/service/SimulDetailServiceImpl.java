package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.statistic.entity.SimulDetail;
import com.ssafy.nashda.statistic.entity.SimulStatistic;
import com.ssafy.nashda.statistic.entity.SimulType;
import com.ssafy.nashda.statistic.repository.SimulDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class SimulDetailServiceImpl implements SimulDetailService {
    private final SimulDetailRepository simulDetailRepository;

    @Override
    @Transactional
    public Long createSimulDetail(SimulStatistic simulStatistic, String my_speech, String gpt_speech, SimulType type) {
        return simulDetailRepository.save(SimulDetail.builder()
                .simulStatistic(simulStatistic)
                .my_speech(my_speech)
                .gpt_speech(gpt_speech)
                .type(type)
                .build())
                .getIndex();
    }
}
