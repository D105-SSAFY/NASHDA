package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.dto.response.BlankStatisticResDto;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.week.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
    Optional<GameStatistic> findByMemberAndWeek(Member member, Week week);
    //member의 통계자료 최근 5주치 불러오기
    List<GameStatistic> findTop5ByMemberOrderByWeekDesc(Member member);
    boolean existsByMemberAndWeek(Member member, Week week);
}
