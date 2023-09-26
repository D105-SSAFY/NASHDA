package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.statistic.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Optional<Achievement> findByIndex(Long index);
}
