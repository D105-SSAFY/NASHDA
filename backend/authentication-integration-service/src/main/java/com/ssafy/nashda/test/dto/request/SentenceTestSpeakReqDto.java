package com.ssafy.nashda.test.dto.request;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SentenceTestSpeakReqDto {
    private String index;
    private int order;  //문장의 문제 번호를 의미
}
