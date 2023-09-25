package com.ssafy.nashda.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class InternalBlankTestResDto {
    //이미지와 문장을 보고 빈칸 뚫고 빈칸에 들어갈 말 찾기
    String imgUrl;
    String answer;  //전체 문장
    List<String> word;  //뚫은 빈칸들
    List<String> hint;  //힌트들
}
