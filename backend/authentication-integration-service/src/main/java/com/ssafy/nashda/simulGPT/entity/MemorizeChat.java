package com.ssafy.nashda.simulGPT.entity;

import com.ssafy.nashda.simulGPT.dto.ChatMessageDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Transient;

import java.util.List;

@Document(collection = "memorize_chat")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemorizeChat {

    @Transient
    public static final String SEQUENCE_NAME = "memorize_chat";

    @Id
    private String id;

    private List<ChatMessageDto> messages;

}
