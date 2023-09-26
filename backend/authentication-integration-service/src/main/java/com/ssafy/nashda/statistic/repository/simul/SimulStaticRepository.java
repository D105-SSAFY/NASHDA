package com.ssafy.nashda.statistic.repository.simul;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SimulStaticRepository extends JpaRepository<SimulStatistic, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SimulStatistic c SET c.total = :total WHERE c.index = :index")
    void updateTotal(Long total, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SimulStatistic c SET c.correct = :correct WHERE c.index = :index")
    void updateCorrect(Long correct, Long index);

    SimulStatistic findByMember(Member member);
}
