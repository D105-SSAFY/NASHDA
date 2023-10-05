package com.ssafy.nashda.simulGPT.repository;

import com.ssafy.nashda.simulGPT.entity.MemorizeChat;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ChatGptRepository extends MongoRepository<MemorizeChat, String> {
}
