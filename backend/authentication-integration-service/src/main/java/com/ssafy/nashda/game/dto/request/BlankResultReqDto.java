package com.ssafy.nashda.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlankResultReqDto {
    int total;  //총 문제 개수
    int score;  //점수
    int level;  //난이도
}
