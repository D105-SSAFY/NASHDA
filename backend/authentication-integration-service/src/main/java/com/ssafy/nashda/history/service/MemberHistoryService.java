package com.ssafy.nashda.history.service;

import com.ssafy.nashda.history.dto.HistoryResDto;
import com.ssafy.nashda.member.entity.Member;

public interface MemberHistoryService{
    void increaseWordCount(Member member);
    void increaseSentenceCount(Member member);
    void increaseConversationCount(Member member);
    void increasePracticeCount(Member member);
    void increasePracticeWordCount(Member member);
    void increasePracticeSentenceCount(Member member);
    void increaseGameCount(Member member);
    void increaseGameBlankCount(Member member);
    void increaseGameSpeedCount(Member member);
    void increaseTestCount(Member member);
    void increaseTestSentenceCount(Member member);
    void increaseTestWordCount(Member member);
    void increaseTestWeekCount(Member member);
    void increaseContinuousLoginCount(Member member);
    void initMemberHistory(Member member);
    void plusWordCount(Member member, int count);
    void plusSentenceCount(Member member, int count);
    HistoryResDto getMemberHistory(Member member);
    boolean isExistMemberHistory(Member member);
}
