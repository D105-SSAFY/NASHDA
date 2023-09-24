package com.ssafy.nashda.simulGPT.service;

import com.ssafy.nashda.simulGPT.ChatGptFeignClient;
import com.ssafy.nashda.simulGPT.dto.ChatMessageDto;
import com.ssafy.nashda.simulGPT.dto.ChatReqDto;
import com.ssafy.nashda.simulGPT.dto.ChatResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
