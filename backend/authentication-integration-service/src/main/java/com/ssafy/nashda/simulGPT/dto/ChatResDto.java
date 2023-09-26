package com.ssafy.nashda.simulGPT.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatResDto {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<ChatChoiceDto> choices;
    private UsageDto usage;
    private Boolean correct;
}
