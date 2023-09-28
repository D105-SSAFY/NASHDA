package com.ssafy.nashda.statistic.repository.simul;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import com.ssafy.nashda.statistic.entity.simul.SimulType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SimulStaticRepository extends JpaRepository<SimulStatistic, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SimulStatistic c SET c.total = :total WHERE c.index = :index")
    void updateTotal(Long total, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SimulStatistic c SET c.correct = :correct WHERE c.index = :index")
    void updateCorrect(Long correct, Long index);

    Optional<SimulStatistic> findByMemberAndSimulType(Member member, SimulType simulType);


    @Query("SELECT SUM(s.total) FROM SimulStatistic s WHERE s.member = :member")
    Long sumTotalByMember(Member member);

    @Query("SELECT SUM(s.correct) FROM SimulStatistic s WHERE s.member = :member")
    Long sumCorrectByMember(Member member);

}
