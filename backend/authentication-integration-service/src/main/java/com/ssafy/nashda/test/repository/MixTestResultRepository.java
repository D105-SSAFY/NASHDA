package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.MixTestResult;
import com.ssafy.nashda.test.entity.SentenceTestResult;
import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MixTestResultRepository extends MongoRepository<MixTestResult, String> {
    List<MixTestResult> findByMemberNumberAndWeekOrderByTryCount(long memberNumber, long week);

    List<MixTestResult> findByMemberNumberOrderByWeekAscTryCountAsc(long memberNumber);

    @Query(value = "{'memberNumber': ?0}", fields = "{'week': 1}")
    List<MixTestResult> findDistinctWeeksByMemberNumber(long memberNumber, Sort sort);
//    List<MixTestResult> findDistinctWeeksByMemberNumber(long memberNumber, Sort sort);
    List<MixTestResult> findByMemberNumberAndWeekIn(long memberNumber, Set<Long> weeks);

}
