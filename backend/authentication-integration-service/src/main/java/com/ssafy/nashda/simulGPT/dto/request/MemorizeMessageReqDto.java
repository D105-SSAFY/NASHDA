package com.ssafy.nashda.simulGPT.dto.request;

import com.ssafy.nashda.simulGPT.dto.response.ChatMessageDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class MemorizeMessageReqDto {
    private String id;
    private List<ChatMessageDto> messages;
}
