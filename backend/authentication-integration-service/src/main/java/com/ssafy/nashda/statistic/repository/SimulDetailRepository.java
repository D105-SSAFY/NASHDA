package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.entity.SimulDetail;
import com.ssafy.nashda.statistic.entity.SimulStatistic;
import com.ssafy.nashda.statistic.entity.SimulType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulDetailRepository extends JpaRepository<SimulDetail, Long> {

    List<SimulDetail> findAllBySimulAndType(SimulStatistic simulStatistic, SimulType simulType);
}
