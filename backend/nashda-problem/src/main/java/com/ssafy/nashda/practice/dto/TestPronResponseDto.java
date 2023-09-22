package com.ssafy.nashda.practice.dto;

import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TestPronResponseDto {
    private List<String> problem;
    private List<String> convert;


    // 생성자
    public TestPronResponseDto(List<?> pronSetList){
        this.problem = new ArrayList<>();
        this.convert = new ArrayList<>();

        if(pronSetList.get(0) instanceof PronWordSet){
            for(Object pronSet : pronSetList){
                problem.add(((PronWordSet) pronSet).getOriginSentence());
                convert.add(((PronWordSet) pronSet).getPronunciation());
            }
        } else if(pronSetList.get(0) instanceof PronPhaseSet){
            for(Object pronSet : pronSetList){
                problem.add(((PronPhaseSet) pronSet).getOriginSentence());
                convert.add(((PronPhaseSet) pronSet).getPronunciation());
            }
        } else if (pronSetList.get(0) instanceof PronSimpleSet){
            for(Object pronSet : pronSetList){
                problem.add(((PronSimpleSet) pronSet).getOriginSentence());
                convert.add(((PronSimpleSet) pronSet).getPronunciation());
            }
        } else if(pronSetList.get(0) instanceof PronComplexSet){
            for(Object pronSet : pronSetList){
                problem.add(((PronComplexSet) pronSet).getOriginSentence());
                convert.add(((PronComplexSet) pronSet).getPronunciation());
            }
        }


    }

}
