package com.ssafy.nashda.practice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticePronRequestDto {
    private long index;
    private String type;
    private MultipartFile sound;
}
