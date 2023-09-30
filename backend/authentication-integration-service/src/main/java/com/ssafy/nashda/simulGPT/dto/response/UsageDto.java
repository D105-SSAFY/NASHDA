package com.ssafy.nashda.simulGPT.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
public class UsageDto {
    private Long prompt_tokens;
    private Long completion_tokens;
    private Long total_tokens;
}
