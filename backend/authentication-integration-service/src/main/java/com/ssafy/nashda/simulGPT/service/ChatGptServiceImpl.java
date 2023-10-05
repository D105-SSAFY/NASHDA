package com.ssafy.nashda.simulGPT.service;

import com.ssafy.nashda.simulGPT.ChatGptFeignClient;
import com.ssafy.nashda.simulGPT.dto.response.ChatMessageDto;
import com.ssafy.nashda.simulGPT.dto.request.ChatReqDto;
import com.ssafy.nashda.simulGPT.dto.response.ChatResDto;
import com.ssafy.nashda.simulGPT.dto.response.ChatSttResDto;
import com.ssafy.nashda.stt.service.STTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGptServiceImpl implements ChatGptService {

    private final STTService sttService;

    @Value("${chatgpt.api-key}")
    String openAIAPIKey;

    private final ChatGptFeignClient chatGptFeignClient;

    public ChatResDto getChatCompletion(List<ChatMessageDto> messages) {
        ChatResDto chatResDto = chatGptFeignClient.chatCompletion(
                ChatReqDto.builder()
                        .messages(messages)
                        .build()
                );
        return chatResDto;

    }

    @Override
    public ChatSttResDto getStt(MultipartFile sound) throws Exception {
//        WebClient webClient = WebClient.builder().build();
//
//        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
//        formData.add("file", sound.getResource());
//        formData.add("model", "whisper-1");
//
//        ChatSttResDto response = webClient.post()
//                .uri("https://api.openai.com/v1/audio/transcriptions")
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openAIAPIKey)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(formData))
//                .retrieve()
//                .bodyToMono(ChatSttResDto.class)
//                .block();

        return new ChatSttResDto(sttService.getText(sound));
    }

}
