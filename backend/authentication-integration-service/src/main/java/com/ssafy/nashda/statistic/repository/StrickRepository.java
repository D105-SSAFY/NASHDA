package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StrickRepository extends JpaRepository<Strick, Long> {
    List<Strick> findByMember(Member member, Pageable pageable);

    Optional<Strick> findByMemberAndCreatOn(Member member, LocalDate date);

    boolean existsByMemberAndCreatOn(Member member, LocalDate date);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Strick s SET s.conversationCount = :count WHERE s.index = :index")
    void updateConversationCount(int count, Long index);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Strick s SET s.blankCount = :count WHERE s.index = :index")
    void updateBlankCount(@Param("count") int count, @Param("index") Long index);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Strick s SET s.practiceCount = :count WHERE s.index = :index")
    void updatePracticeCount(@Param("count") int count, @Param("index") Long index);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Strick s SET s.speedCount = :count WHERE s.index = :index")
    void updateSpeedCount(@Param("count") int count, @Param("index") Long index);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Strick s SET s.testCount = :count WHERE s.index = :index")
    void updateTestCount(@Param("count") int count, @Param("index") Long index);
}
