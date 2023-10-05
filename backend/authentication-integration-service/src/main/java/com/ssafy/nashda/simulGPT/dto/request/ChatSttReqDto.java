package com.ssafy.nashda.simulGPT.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ChatSttReqDto {
    private MultipartFile file;
    private String model;

    @Builder
    public ChatSttReqDto(MultipartFile file) {
        this.file = file;
        this.model = "whisper-1";
    }
}
