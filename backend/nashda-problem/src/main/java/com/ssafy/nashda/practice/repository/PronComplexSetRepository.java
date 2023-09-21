package com.ssafy.nashda.practice.repository;

import com.ssafy.nashda.practice.entity.PronComplexSet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * 발음 - 복합절 Collection MongoDB Spring Data 인터페이스
 * 2023-09-19
 * 조경호
 * */
public interface PronComplexSetRepository extends MongoRepository<PronComplexSet, String> {
    public Optional<PronComplexSet> findByNum(int num);
}