package com.ssafy.nashda.member.repository;

import com.ssafy.nashda.member.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByJobIdx(Long jobIndex);
}
