package com.ssafy.nashda.simulGPT.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MessageReqDto {
    private String cacheId;

    @NotNull
    @NotEmpty
    private String message;
}
