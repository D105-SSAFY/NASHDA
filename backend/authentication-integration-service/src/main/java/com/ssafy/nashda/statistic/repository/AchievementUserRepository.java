package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.AchievementUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementUserRepository extends JpaRepository<AchievementUser, Long> {
    Optional<AchievementUser> findByMember(Member member);
}
