package com.ssafy.nashda.member.repository;

import com.ssafy.nashda.member.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByHobbyIdx(Long hobbyIdx);
}
