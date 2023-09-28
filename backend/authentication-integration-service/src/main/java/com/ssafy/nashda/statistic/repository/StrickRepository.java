package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StrickRepository extends JpaRepository<Strick, Long> {
    List<Strick> findByMember(Member member, Pageable pageable);
    Optional<Strick>findByMemberAndCreatOn(Member member, LocalDate date);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Strick s SET s.conversationCount = :count WHERE s.index = :index")
    void updateConversationCount(int count, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Strick s SET s.blankCount = :count WHERE s.index = :index")
    void updateBlankCount(int count, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Strick s SET s.practiceCount = :count WHERE s.index = :index")
    void updatePracticeCount(int count, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Strick s SET s.speedCount = :count WHERE s.index = :index")
    void updateSpeedCount(int count, Long index);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Strick s SET s.testCount = :count WHERE s.index = :index")
    void updateTestCount(int count, Long index);
}
