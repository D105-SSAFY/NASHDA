package com.ssafy.nashda.game.repository;

import com.ssafy.nashda.game.entity.ImgWordHint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImgWordHintRepository extends MongoRepository<ImgWordHint, String> {
}
