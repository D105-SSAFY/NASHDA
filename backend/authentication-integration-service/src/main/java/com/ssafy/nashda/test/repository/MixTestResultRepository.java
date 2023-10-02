package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.MixTestResult;
import com.ssafy.nashda.test.entity.SentenceTestResult;
import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MixTestResultRepository extends MongoRepository<MixTestResult, String> {
    List<MixTestResult> findByMemberNumberAndWeek(long memberNumber, long week);
    List<MixTestResult> findByMemberNumberOrderByWeekAscTryCountAsc(long memberNumber);
}
