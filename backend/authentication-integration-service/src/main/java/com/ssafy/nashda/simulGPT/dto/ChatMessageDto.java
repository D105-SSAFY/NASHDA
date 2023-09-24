package com.ssafy.nashda.simulGPT.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDto {
    private String role;
    private String content;

    @Builder
    public ChatMessageDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
}