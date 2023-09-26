package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Achievement;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAchievement(Member member);
}
