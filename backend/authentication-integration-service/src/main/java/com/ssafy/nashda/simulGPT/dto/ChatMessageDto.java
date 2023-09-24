package com.ssafy.nashda.simulGPT.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDto {
    private String role;
    private String content;
}