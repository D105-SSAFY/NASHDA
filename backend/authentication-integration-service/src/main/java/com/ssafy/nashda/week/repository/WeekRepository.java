package com.ssafy.nashda.week.repository;

import com.ssafy.nashda.week.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {
    Optional<Week> findByWeekIdx(Long weekIdx);
    @Query(value = "select * from week where start_date <= now() and end_date >= now()", nativeQuery = true)
    Optional<Week> findCurrentWeek();
}
