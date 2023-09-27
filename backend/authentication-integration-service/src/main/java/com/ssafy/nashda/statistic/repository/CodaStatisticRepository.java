package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.PhonemeInterface;
import com.ssafy.nashda.statistic.entity.CodaStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CodaStatisticRepository extends JpaRepository<CodaStatistic, Long> {
    Optional<CodaStatistic> findByMemberAndLetter(Member member, String letter);



    @Query(value = "SELECT incorrect, letter, total, 1 AS type FROM practice_statistic_onset WHERE member_number = :memberId" +
            " UNION ALL" +
            " SELECT incorrect, letter, total, 2 AS type FROM practice_statistic_nucleus WHERE member_number = :memberId" +
            " UNION all" +
            " SELECT incorrect, letter, total, 3 AS type FROM practice_statistic_coda WHERE member_number = :memberId" +
            " ORDER BY incorrect DESC LIMIT 10;", nativeQuery = true)
    List<PhonemeInterface> findByIncorrectDESC(Long memberId);
}
