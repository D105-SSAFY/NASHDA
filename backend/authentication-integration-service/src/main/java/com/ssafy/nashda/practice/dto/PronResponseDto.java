package com.ssafy.nashda.practice.dto;

import com.ssafy.nashda.practice.entity.PronWordSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PronResponseDto {
    private String index;
    private String origin;
    private String convert;

    public PronResponseDto(PronWordSet pronunciationWordSet){
        this.index = pronunciationWordSet.getId();
        this.origin = pronunciationWordSet.getOriginSentence();
        this.convert = pronunciationWordSet.getPronunciation();
    }
}
