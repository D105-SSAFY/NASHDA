package com.ssafy.nashda.statistic.service.simul;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import com.ssafy.nashda.statistic.repository.simul.SimulStaticRepository;
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
    public void createSimulStatic(Member member) {
        simulStatisticRepository.save(SimulStatistic.builder()
                                        .member(member)
                                        .build());
    }

}
