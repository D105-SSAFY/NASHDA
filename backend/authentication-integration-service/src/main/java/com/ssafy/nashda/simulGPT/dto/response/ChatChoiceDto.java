package com.ssafy.nashda.simulGPT.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatChoiceDto {
    private Long index;
    private ChatMessageDto message;
    private String finish_reason;
}
