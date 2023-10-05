package com.ssafy.nashda.history.service;

import com.ssafy.nashda.history.dto.HistoryResDto;
import com.ssafy.nashda.history.entity.MemberHistory;
import com.ssafy.nashda.history.repository.MemberHistoryRepository;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.service.AchievementService;
import com.ssafy.nashda.statistic.service.StrickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberHistoryServiceImpl implements MemberHistoryService {
    private final MemberHistoryRepository memberHistoryRepository;
    private final AchievementService achievementService;
    private final StrickService strickService;

    @Override
    public void increasePracticeCount(Member member) {
        memberHistoryRepository.increasePracticeCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "practice", history.getPracticeCount());
    }

    @Override
    public void increasePracticeWordCount(Member member) {
        memberHistoryRepository.increasePracticeWordCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "practice_word", history.getPracticeWordCount());
    }

    @Override
    public void increasePracticeSentenceCount(Member member) {
        memberHistoryRepository.increasePracticeSentenceCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "practice_sentence", history.getPracticeSentenceCount());
    }

    @Override
    public void increaseGameCount(Member member) {
        memberHistoryRepository.increaseGameCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "game", history.getGameCount());
    }

    @Override
    public void increaseGameBlankCount(Member member) {
        memberHistoryRepository.increaseGameBlankCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        increaseGameCount(member);
        achievementService.updateMemberAchievement(member, "game_blank", history.getGameBlankCount());
    }

    @Override
    public void increaseGameSpeedCount(Member member) {
        memberHistoryRepository.increaseGameSpeedCount(member);
        increaseGameCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "game_speed", history.getGameSpeedCount());
    }

    @Override
    public void increaseWordCount(Member member) {
        memberHistoryRepository.increaseWordCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "word", history.getWordCount());
    }

    @Override
    public void increaseSentenceCount(Member member) {
        memberHistoryRepository.increaseSentenceCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "sentence", history.getSentenceCount());
    }

    @Override
    public void increaseConversationCount(Member member) {
        memberHistoryRepository.increaseConversationCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "conversation", history.getConversationCount());
    }

    @Override
    public void increaseTestCount(Member member) {
        memberHistoryRepository.increaseTestCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "test", history.getTestCount());
    }

    @Override
    public void increaseTestSentenceCount(Member member) {
        memberHistoryRepository.increaseTestSpeedCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        increaseTestCount(member);
        achievementService.updateMemberAchievement(member, "test_speed", history.getTestSentenceCount());
    }

    @Override
    public void increaseTestWordCount(Member member) {
        memberHistoryRepository.increaseTestWordCount(member);
        increaseTestCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "test_word", history.getTestWordCount());
    }

    @Override
    public void increaseTestWeekCount(Member member) {
        memberHistoryRepository.increaseTestWeekCount(member);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        increaseTestCount(member);
        achievementService.updateMemberAchievement(member, "test_week", history.getTestWeekCount());
    }

    @Override
    public void increaseContinuousLoginCount(Member member) {
        if(strickService.isExistStrickBefore(member)){
            memberHistoryRepository.increaseContinuousLoginCount(member);
            MemberHistory history = memberHistoryRepository.findByMember(member);
            achievementService.updateMemberAchievement(member, "continuous_login", history.getContinuousLoginCount());
        }else {
            memberHistoryRepository.initMemberContinuousLoginCount(member);
        }
    }

    @Override
    public void initMemberHistory(Member member) {
        MemberHistory memberHistory = MemberHistory.builder()
                .member(member)
                .build();
        memberHistoryRepository.save(memberHistory);
    }

    @Override
    public void plusWordCount(Member member, int count) {
        memberHistoryRepository.plusWordCount(member, count);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "word", history.getWordCount());
    }

    @Override
    public void plusSentenceCount(Member member, int count) {
        memberHistoryRepository.plusSentenceCount(member, count);
        MemberHistory history = memberHistoryRepository.findByMember(member);
        achievementService.updateMemberAchievement(member, "sentence", history.getSentenceCount());
    }

    @Override
    public HistoryResDto getMemberHistory(Member member) {
        MemberHistory history = memberHistoryRepository.findByMember(member);
        return new HistoryResDto(history);
    }

    @Override
    public boolean isExistMemberHistory(Member member) {
       return memberHistoryRepository.existsByMember(member);
    }
}
