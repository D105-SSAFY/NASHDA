package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Achievement;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAchievementRepository  extends JpaRepository<MemberAchievement, Long> {
    List<MemberAchievement> findByMember(Member member);

}
