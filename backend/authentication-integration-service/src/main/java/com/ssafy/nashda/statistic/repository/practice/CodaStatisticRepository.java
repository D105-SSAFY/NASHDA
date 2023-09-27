package com.ssafy.nashda.statistic.repository.practice;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.practice.CodaStatistic;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodaStatisticRepository extends JpaRepository<CodaStatistic, Long> {
    Optional<CodaStatistic> findByMemberAndLetter(Member member, String letter);
}
