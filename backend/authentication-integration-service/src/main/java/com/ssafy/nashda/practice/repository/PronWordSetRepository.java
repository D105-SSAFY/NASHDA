package com.ssafy.nashda.practice.repository;


import com.ssafy.nashda.practice.entity.PronWordSet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PronWordSetRepository extends MongoRepository<PronWordSet, String>{
    public Optional<PronWordSet> findByNum(int num);
}
