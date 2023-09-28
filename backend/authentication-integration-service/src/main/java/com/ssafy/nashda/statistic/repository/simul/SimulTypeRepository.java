package com.ssafy.nashda.statistic.repository.simul;

import com.ssafy.nashda.statistic.entity.simul.SimulType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulTypeRepository extends JpaRepository<SimulType, Long> {

    SimulType findByName(String name);

}
