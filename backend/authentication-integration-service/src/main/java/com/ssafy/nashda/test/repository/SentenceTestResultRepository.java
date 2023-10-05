package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.SentenceTestResult;
import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SentenceTestResultRepository extends MongoRepository<SentenceTestResult, String> {

    List<SentenceTestResult> findByMemberNumberAndWeek(long memberNumber, long week);
    Optional<SentenceTestResult> findById(String id);

}
