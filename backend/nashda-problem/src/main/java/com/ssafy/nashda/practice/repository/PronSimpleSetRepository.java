package com.ssafy.nashda.practice.repository;

import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * 발음 - 절 Collection MongoDB Spring Data 인터페이스
 * 2023-09-19
 * 조경호
 * */
public interface PronSimpleSetRepository extends MongoRepository<PronSimpleSet, String> {
    public Optional<PronSimpleSet> findByNum(int num);

    @Aggregation(pipeline = {"{'$sample':{size: 5}}",})
    public List<PronSimpleSet> findRandomSimple();
}
