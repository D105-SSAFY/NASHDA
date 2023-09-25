package com.ssafy.nashda.simulGPT.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.simulGPT.ChatGptFeignClient;
import com.ssafy.nashda.simulGPT.dto.ChatMessageDto;
import com.ssafy.nashda.simulGPT.dto.ChatReqDto;
import com.ssafy.nashda.simulGPT.dto.ChatResDto;
import com.ssafy.nashda.simulGPT.entity.MemorizeChat;
import com.ssafy.nashda.simulGPT.repository.ChatGptRepository;
import com.ssafy.nashda.test.entity.SentenceTestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGptServiceImpl implements ChatGptService {

    private final ChatGptFeignClient chatGptFeignClient;
    private final ChatGptRepository chatGptRepository;

    public ChatResDto getChatCompletion(List<ChatMessageDto> messages) {
        ChatResDto chatResDto = chatGptFeignClient.chatCompletion(
                ChatReqDto.builder()
                        .messages(messages)
                        .build()
                );
        return chatResDto;

    }

}
