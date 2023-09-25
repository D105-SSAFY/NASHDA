package com.ssafy.nashda.game.repository;

import com.ssafy.nashda.game.entity.ImgWordSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ImgWordSetRepository extends MongoRepository<ImgWordSet, String> {
    Optional<ImgWordSet> findByNum(long num);

    @Aggregation(pipeline = {
            "{'$match':{'num': {$ne: ?0} }}",
            "{'$sample':{size:3}}"})
    public List<ImgWordSet> findRandom(long index);

}
