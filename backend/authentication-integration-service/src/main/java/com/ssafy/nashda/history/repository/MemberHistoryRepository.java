package com.ssafy.nashda.history.repository;

import com.ssafy.nashda.history.entity.MemberHistory;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.wordCount=m.wordCount+1 where m.member = :member")
    void increaseWordCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.wordCount=m.wordCount+ :count where m.member = :member")
    void plusWordCount(@Param("member") Member member, @Param("count") int count);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.sentenceCount=m.sentenceCount+1 where m.member = :member")
    void increaseSentenceCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.sentenceCount=m.sentenceCount+ :count where m.member = :member")
    void plusSentenceCount(@Param("member") Member member, @Param("count") int count);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.conversationCount=m.conversationCount+1 where m.member = :member")
    void increaseConversationCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.practiceCount=m.practiceCount+1 where m.member = :member")
    void increasePracticeCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.practiceWordCount=m.practiceWordCount+1 where m.member = :member")
    void increasePracticeWordCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.practiceSentenceCount=m.practiceSentenceCount+1 where m.member = :member")
    void increasePracticeSentenceCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.gameCount=m.gameCount+1 where m.member = :member")
    void increaseGameCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.gameBlankCount=m.gameBlankCount+1 where m.member = :member")
    void increaseGameBlankCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.gameSpeedCount=m.gameSpeedCount+1 where m.member = :member")
    void increaseGameSpeedCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.testCount=m.testCount+1 where m.member = :member")
    void increaseTestCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.testSentenceCount=m.testSentenceCount+1 where m.member = :member")
    void increaseTestSpeedCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.testWordCount=m.testWordCount+1 where m.member = :member")
    void increaseTestWordCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.testWeekCount=m.testWeekCount+1 where m.member = :member")
    void increaseTestWeekCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.continuousLoginCount=m.continuousLoginCount+1 where m.member = :member")
    void increaseContinuousLoginCount(@Param("member") Member member);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update MemberHistory m set m.continuousLoginCount=0 where m.member = :member")
    void initMemberContinuousLoginCount(@Param("member") Member member);

    MemberHistory findByMember(Member member);
    boolean existsByMember(Member member);
}
