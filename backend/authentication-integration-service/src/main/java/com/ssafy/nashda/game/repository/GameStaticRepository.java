package com.ssafy.nashda.game.repository;

import com.ssafy.nashda.game.entity.GameStatistic;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStaticRepository extends JpaRepository<GameStatistic, Long> {

    //Member로 조회
    Optional<GameStatistic> findByMember(Member member);
}
