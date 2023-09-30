package com.ssafy.nashda.member.repository;

import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
/*    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.conversationCount = :count WHERE m.memberNum = :memberNum")
    void updateConversationCount(int count, Long memberNum);*/

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Member m SET m.status = 3 WHERE m.memberNum = :memberNum")
    void unRegist(Long memberNum);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.progress = m.progress+ :count WHERE m.memberNum = :memberNum")
    void plusProgress(@Param("count") int count, @Param("memberNum") Long memberNum);


/*
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.wordCount = m.wordCount + :count WHERE m.memberNum = :memberNum")
    void updateWordCount(int count, Long memberNum);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.wordCount = m.sentenceCount + :count WHERE m.memberNum = :memberNum")
    void updateSentenceCount(int count, Long memberNum);
*/


/*    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.wordCount = m.wordCount + :count WHERE m.memberNum = :memberNum")
    void updateWordCount(@Param("count") int count, @Param("memberNum") Long memberNum);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.sentenceCount = m.sentenceCount + :count WHERE m.memberNum = :memberNum")
    void updateSentenceCount(@Param("count") int count, @Param("memberNum") Long memberNum);*/


}
