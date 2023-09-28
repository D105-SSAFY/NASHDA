package com.ssafy.nashda.statistic.repository.simul;

import com.ssafy.nashda.statistic.entity.simul.SimulDetail;
import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import com.ssafy.nashda.statistic.entity.simul.SimulType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulDetailRepository extends JpaRepository<SimulDetail, Long> {

    List<SimulDetail> findAllBySimulAndType(SimulStatistic simulStatistic, SimulType simulType);
}
