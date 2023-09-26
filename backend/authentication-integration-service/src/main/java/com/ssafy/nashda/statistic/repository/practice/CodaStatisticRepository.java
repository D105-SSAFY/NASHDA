package com.ssafy.nashda.statistic.repository.practice;

import com.ssafy.nashda.statistic.entity.practice.CodaStatistic;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodaStatisticRepository extends JpaRepository<CodaStatistic, Long> {
}
