package com.ssafy.nashda.simulGPT.service;

import com.ssafy.nashda.simulGPT.ChatGptFeignClient;
import com.ssafy.nashda.simulGPT.dto.ChatMessageDto;
import com.ssafy.nashda.simulGPT.dto.ChatReqDto;
import com.ssafy.nashda.simulGPT.dto.ChatResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGptServiceImpl {

    private final ChatGptFeignClient chatGptFeignClient;

    public ChatResDto getChatCompletion(List<ChatMessageDto> messages) {
        ChatResDto chatResDto = chatGptFeignClient.chatCompletion(
                ChatReqDto.builder()
                        .messages(messages)
                        .build()
                );
        return chatResDto;

    }
}
