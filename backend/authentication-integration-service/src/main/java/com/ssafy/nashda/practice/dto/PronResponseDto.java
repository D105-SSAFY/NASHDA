package com.ssafy.nashda.practice.dto;

import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
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
        this.index = Long.toString(pronunciationWordSet.getNum());
        this.origin = pronunciationWordSet.getOriginSentence();
        this.convert = pronunciationWordSet.getPronunciation();
    }

    public PronResponseDto(PronPhaseSet pronunciationPhaseSet){
        this.index = Long.toString(pronunciationPhaseSet.getNum());
        this.origin = pronunciationPhaseSet.getOriginSentence();
        this.convert = pronunciationPhaseSet.getPronunciation();
    }

    public PronResponseDto(PronSimpleSet pronunciationSimpleSet){
        this.index = Long.toString(pronunciationSimpleSet.getNum());
        this.origin = pronunciationSimpleSet.getOriginSentence();
        this.convert = pronunciationSimpleSet.getPronunciation();
    }

    public PronResponseDto(PronComplexSet pronunciationComplexSet){
        this.index = Long.toString(pronunciationComplexSet.getNum());
        this.origin = pronunciationComplexSet.getOriginSentence();
        this.convert = pronunciationComplexSet.getPronunciation();
    }
}
