package com.ssafy.nashda.practice.repository;

import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * 발음 - 구 Collection MongoDB Spring Data 인터페이스
 * 2023-09-19
 * 조경호
 * */
public interface PronPhaseSetRepository extends MongoRepository<PronPhaseSet, String> {
    public Optional<PronPhaseSet> findByNum(int num);
}
