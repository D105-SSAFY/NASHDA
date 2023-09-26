package com.ssafy.nashda.statistic.repository.game;

import com.ssafy.nashda.statistic.entity.game.GameStatistic;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {

    //Member로 조회
    Optional<GameStatistic> findByMember(Member member);
}
