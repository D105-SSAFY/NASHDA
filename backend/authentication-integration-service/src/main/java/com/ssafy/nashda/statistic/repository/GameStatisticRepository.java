package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.dto.response.BlankStatisticResDto;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
    Optional<GameStatistic> findByMember(Member member);

}
