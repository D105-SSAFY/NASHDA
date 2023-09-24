package com.ssafy.nashda.simulGPT.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class MemorizeMessageReqDto {
    private String cacheId;
    private List<ChatMessageDto> messages;
}
