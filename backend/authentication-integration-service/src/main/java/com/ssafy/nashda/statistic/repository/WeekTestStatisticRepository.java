package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.WeekTestStatistic;
import com.ssafy.nashda.week.entity.Week;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeekTestStatisticRepository extends JpaRepository<WeekTestStatistic, Long> {
    //member의 week가 큰것 부터 5개 뽑기
    List<WeekTestStatistic> findByMemberOrderByWeekDesc(Member member, Pageable pageable);
    boolean existsByMemberAndWeek(Member member, Week week);

    @Modifying(clearAutomatically = true)
    @Query("update WeekTestStatistic w set w.maxScore = :newScore, w.testDetail= :index WHERE w.member = :member and w.week = :week and w.maxScore < :newScore")
    void updateScore(@Param("member") Member member, @Param("week") Week week, @Param("newScore") int newScore, @Param("index") String index);

}
