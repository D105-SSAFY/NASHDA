package com.ssafy.nashda.game.repository;

import com.ssafy.nashda.game.entity.ImgWordHint;
import com.ssafy.nashda.game.entity.ImgWordSet;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ImgWordHintRepository extends MongoRepository<ImgWordHint, String> {

}
