package com.ssafy.nashda.test.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.WordTestResultReqDto;
import com.ssafy.nashda.test.dto.response.TestStartWordResDto;

public interface TestService {
    TestStartWordResDto wordTestStart(Member member);
    void saveWordTestScore(WordTestResultReqDto reqDto);
    TestStartWordResDto sentenceTestStart(Member member);
    void saveSentenceTestScore(String index, int score);
}
