package com.ssafy.nashda.simulGPT.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class MemorizeMessageReqDto {
    private String id;
    private List<ChatMessageDto> messages;
}
