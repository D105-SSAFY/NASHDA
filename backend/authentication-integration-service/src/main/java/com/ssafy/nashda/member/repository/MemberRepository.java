package com.ssafy.nashda.member.repository;

import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.conversationCount = :count WHERE m.memberNum = :memberNum")
    void updateConversationCount(int count, Long memberNum);
}
