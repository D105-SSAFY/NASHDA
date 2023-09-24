package com.ssafy.nashda.simulGPT.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MessageReqDto implements Serializable {
    private String cacheId;

    @NotNull
    @NotEmpty
    private String message;
}
