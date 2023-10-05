package com.ssafy.nashda.game.repository;

import com.ssafy.nashda.game.entity.BlankQuestionSet;
import com.ssafy.nashda.game.entity.ImgWordSet;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlankQuestionSetRepository extends MongoRepository<BlankQuestionSet, String> {

    @Aggregation(pipeline = {
            "{'$sample':{size:4}}"})
    List<BlankQuestionSet> findRandom();

}
