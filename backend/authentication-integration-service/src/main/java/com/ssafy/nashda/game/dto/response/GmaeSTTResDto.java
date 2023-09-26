package com.ssafy.nashda.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GmaeSTTResDto {
    boolean result; //틀렸는지 맞았는지 반환
    String convert; //변환된 문자열
}
