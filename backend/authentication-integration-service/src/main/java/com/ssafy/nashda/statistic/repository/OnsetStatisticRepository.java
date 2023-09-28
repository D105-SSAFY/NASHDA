package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.OnsetStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnsetStatisticRepository extends JpaRepository<OnsetStatistic, Long> {

    Optional<OnsetStatistic> findByMemberAndLetter(Member member, String letter);
}
