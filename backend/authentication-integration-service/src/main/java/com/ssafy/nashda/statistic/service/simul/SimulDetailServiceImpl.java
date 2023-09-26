package com.ssafy.nashda.statistic.service.simul;

import com.ssafy.nashda.statistic.entity.simul.SimulDetail;
import com.ssafy.nashda.statistic.entity.simul.SimulStatic;
import com.ssafy.nashda.statistic.repository.simul.SimulDetailRepository;
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
    public Long createSimulDetail(SimulStatic simulStatic, String my_speech, String gpt_speech, String type) {
        return simulDetailRepository.save(SimulDetail.builder()
                .simulStatic(simulStatic)
                .my_speech(my_speech)
                .gpt_speech(gpt_speech)
                .type(type)
                .build())
                .getIndex();
    }
}
