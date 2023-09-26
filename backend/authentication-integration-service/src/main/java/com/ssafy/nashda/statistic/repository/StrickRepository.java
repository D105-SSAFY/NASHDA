package com.ssafy.nashda.statistic.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StrickRepository extends JpaRepository<Strick, Long> {
    List<Strick> findByMember(Member member, Pageable pageable);
    Optional<Strick>findByMemberAndCreatOn(Member member, LocalDate date);
}
