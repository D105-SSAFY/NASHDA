package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.MixTestResult;
import com.ssafy.nashda.test.entity.SentenceTestResult;
import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MixTestResultRepository extends MongoRepository<MixTestResult, String> {
    List<MixTestResult> findByMemberNumberAndWeekOrderByTryCount(long memberNumber, long week);
    List<MixTestResult> findByMemberNumberOrderByWeekAscTryCountAsc(long memberNumber);
//    Optional<MixTestResult> findByMemberNumberAndWeekAndTryCount(long memberNumber, long week, int tryCount);
}
