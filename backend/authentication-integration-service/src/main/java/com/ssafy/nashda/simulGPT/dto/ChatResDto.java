package com.ssafy.nashda.simulGPT.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class ChatResDto {
    private String id;
    private String object;
    private Long created;
    private String model;
    private ChatChoiceDto[] choices;
    private UsageDto usage;
}
