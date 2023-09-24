package com.ssafy.nashda.simulGPT.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatChoiceDto {
    private Long index;
    private ChatMessageDto message;
    private String finish_reason;
}
