package com.ssafy.nashda.simulGPT.service;

import com.ssafy.nashda.simulGPT.dto.response.ChatMessageDto;
import com.ssafy.nashda.simulGPT.dto.response.ChatResDto;
import com.ssafy.nashda.simulGPT.dto.response.ChatSttResDto;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ChatGptService {

    public ChatResDto getChatCompletion(List<ChatMessageDto> messages);

    public ChatSttResDto getStt(MultipartFile sound) throws Exception;

}
