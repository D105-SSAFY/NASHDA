package com.ssafy.nashda.simulGPT.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MessageReqDto implements Serializable {
    private String id;

    @NotNull
    @NotEmpty
    private String message;
}
