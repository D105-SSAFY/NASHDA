package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.dto.response.BlankStatisticResDto;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.week.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
    Optional<GameStatistic> findByMemberAndWeek(Member member, Week week);
    List<GameStatistic> findTop5ByMemberOrderByWeekDesc(Member member);
    boolean existsByMemberAndWeek(Member member, Week week);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update GameStatistic g set g.blankScore=g.blankScore+ :score where g.member = :member and g.week = :week")
    void plusBlankScore(@Param("member") Member member, @Param("score") int score, @Param("week") Week week);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update GameStatistic g set g.blankSet=g.blankSet+1 where g.member = :member and g.week = :week")
    void increaseBlankSet(@Param("member") Member member, @Param("week") Week week);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update GameStatistic g set g.blankTotal=g.blankTotal+ :count where g.member = :member and g.week = :week")
    void plusBlankTotal(@Param("member") Member member, @Param("count") int count, @Param("week") Week week);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update GameStatistic g set g.speedScore=g.speedScore+ :score where g.member = :member and g.week = :week")
    void plusSpeedScore(@Param("member") Member member, @Param("score") int score, @Param("week") Week week);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update GameStatistic g set g.speedSet=g.speedSet+1 where g.member = :member and g.week = :week")
    void increaseSpeedSet(@Param("member") Member member, @Param("week") Week week);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update GameStatistic g set g.speedTotal=g.speedTotal+ :count where g.member = :member and g.week = :week")
    void plusSpeedTotal(@Param("member") Member member, @Param("count") int count, @Param("week") Week week);
}
