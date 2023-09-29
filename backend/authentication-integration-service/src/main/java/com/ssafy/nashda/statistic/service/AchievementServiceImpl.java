package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Achievement;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import com.ssafy.nashda.statistic.repository.AchievementRepository;
import com.ssafy.nashda.statistic.repository.MemberAchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final MemberAchievementRepository memberAchievementRepository;

    @Override
    @Transactional
    public List<MemberAchievement> getMemberAchievement(Member member) {
        return new ArrayList<>(member.getMemberAchievements());
    }

    @Override
    @Transactional
    public List<Achievement> getAchievementListByType(String type) {
        return achievementRepository.findByType(type);
    }

    @Override
    @Transactional
    public void updateMemberAchievement(Member member, String type, int count) {
        List<Achievement> achievementList = getAchievementListByType(type);
        List<MemberAchievement> memberAchievementList = memberAchievementRepository.findByMember(member);

        for (Achievement achievement : achievementList) {
            if (count >= achievement.getCondition()) {
                if (memberAchievementList.stream().noneMatch(memberAchievement -> memberAchievement.getAchievement().equals(achievement))) {
                    memberAchievementRepository.save(MemberAchievement.builder()
                            .member(member)
                            .achievement(achievement)
                            .build());

                }
            }
        }
    }
}
