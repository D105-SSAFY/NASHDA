package com.ssafy.nashda.statistic.repository.practice;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OnsetStatisticRepository extends JpaRepository<OnsetStatistic, Long> {

    Optional<OnsetStatistic> findByMemberAndLetter(Member member, String letter);
}
