package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.SentenceTestResult;
import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SentenceTestResultRepository extends MongoRepository<SentenceTestResult, String> {

    //사용자의 member_number와 week를 이용해서 사용자의 몇번 했는지 찾아올 것임!
    List<SentenceTestResult> findByMemberNumberAndWeek(long memberNumber, long week);
    int countByMemberNumberAndWeek(long memberNumber, long week);
    Optional<SentenceTestResult> findById(String id);

}
