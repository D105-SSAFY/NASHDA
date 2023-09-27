package com.ssafy.nashda.statistic.repository.practice;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.practice.NucleusStatistic;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NucleusStatisticRepository extends JpaRepository<NucleusStatistic, Long> {

    Optional<NucleusStatistic> findByMemberAndLetter(Member member, String letter);
}
