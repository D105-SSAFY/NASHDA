package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
    Optional<GameStatistic> findByMember(Member member);
}
