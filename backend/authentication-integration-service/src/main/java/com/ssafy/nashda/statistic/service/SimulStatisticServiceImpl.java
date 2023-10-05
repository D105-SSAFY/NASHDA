package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.SimulStatistic;
import com.ssafy.nashda.statistic.entity.SimulType;
import com.ssafy.nashda.statistic.repository.SimulStaticRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class SimulStatisticServiceImpl implements SimulStatisticService {
    private final SimulStaticRepository simulStatisticRepository;

    @Override
    @Transactional
    public SimulStatistic createSimulStatic(Member member, SimulType type) {
        return simulStatisticRepository.save(SimulStatistic.builder()
                                        .member(member)
                                        .type(type)
                                        .build());
    }

}
