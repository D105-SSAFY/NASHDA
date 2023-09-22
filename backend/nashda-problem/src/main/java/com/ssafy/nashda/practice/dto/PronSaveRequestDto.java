package com.ssafy.nashda.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PronSaveRequestDto {
    private String originSentence;
    private String pronunciation;
    private String job;
    private String hobby;

}
