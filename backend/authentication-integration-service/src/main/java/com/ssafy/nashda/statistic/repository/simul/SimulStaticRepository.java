package com.ssafy.nashda.statistic.repository.simul;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.simul.SimulStatic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SimulStaticRepository extends JpaRepository<SimulStatic, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SimulStatic c SET c.total = :total WHERE c.index = :index")
    void updateTotal(Long total, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SimulStatic c SET c.correct = :correct WHERE c.index = :index")
    void updateCorrect(Long correct, Long index);

    SimulStatic findByMember(Member member);
}
