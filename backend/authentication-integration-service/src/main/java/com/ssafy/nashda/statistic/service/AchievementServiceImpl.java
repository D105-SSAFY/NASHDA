package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Achievement;
import com.ssafy.nashda.statistic.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService{

    private final AchievementRepository achievementRepository;
    @Override
    public List<Achievement> getAchievement(Member member) {
        //member의 업적을 모두 가져온다.

        return null;
    }
}
