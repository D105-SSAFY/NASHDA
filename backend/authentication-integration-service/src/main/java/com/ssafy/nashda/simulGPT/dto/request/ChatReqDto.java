package com.ssafy.nashda.simulGPT.dto.request;

import com.ssafy.nashda.simulGPT.dto.response.ChatMessageDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatReqDto {
    private String model;
    private List<ChatMessageDto> messages;
    private Long max_tokens;
    private Double temperature;

    @Builder
    public ChatReqDto(String model, List<ChatMessageDto> messages, Long max_tokens, Double temperature) {
        this.model = "gpt-3.5-turbo-16k";
        this.messages = messages;
        this.max_tokens = 200L;
        this.temperature = 0D;
    }
}
