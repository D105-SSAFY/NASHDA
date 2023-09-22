package com.ssafy.nashda.practice.repository;


import com.ssafy.nashda.practice.entity.PronWordSet;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * 발음 - 절 Collection MongoDB Spring Data 인터페이스
 * 2023-09-18
 * 조경호
 * */
public interface PronWordSetRepository extends MongoRepository<PronWordSet, String>{
    public Optional<PronWordSet> findByNum(long num);

    @Aggregation(pipeline = {"{'$sample':{size: 10}}",})
    public List<PronWordSet> findRandomWord();

}
