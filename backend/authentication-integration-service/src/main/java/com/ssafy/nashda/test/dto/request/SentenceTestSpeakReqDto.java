package com.ssafy.nashda.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentenceTestSpeakReqDto {
    String index;
    int order;  //문장의 문제 번호를 의미
    MultipartFile sound;
}
