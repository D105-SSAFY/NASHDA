package com.ssafy.nashda.statistic.repository.practice;

import com.ssafy.nashda.statistic.entity.practice.NucleusStatistic;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NucleusStatisticRepository extends JpaRepository<NucleusStatistic, Long> {
}
