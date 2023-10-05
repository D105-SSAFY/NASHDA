package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WordTestResultRepository extends MongoRepository<WordTestResult, String> {
    List<WordTestResult> findByMemberNumberAndWeek(long memberNumber, long week);
    Optional<WordTestResult> findById(String id);

}
