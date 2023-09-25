package com.ssafy.nashda.test.repository;

import com.ssafy.nashda.test.entity.WordTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WordTestResultRepository extends MongoRepository<WordTestResult, String> {

    //사용자의 member_number와 week를 이용해서 사용자의 몇번 했는지 찾아올 것임!
    List<WordTestResult> findByMemberNumberAndWeek(long memberNumber, long week);
    Optional<WordTestResult> findById(String id);

}
