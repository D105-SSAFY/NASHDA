package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.entity.SimulType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulTypeRepository extends JpaRepository<SimulType, Long> {
    SimulType findByName(String name);

}
