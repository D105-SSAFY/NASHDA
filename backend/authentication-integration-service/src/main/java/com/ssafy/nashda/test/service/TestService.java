package com.ssafy.nashda.test.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.SentenceTestSpeakReqDto;
import com.ssafy.nashda.test.dto.request.WordTestResultReqDto;
import com.ssafy.nashda.test.dto.response.TestStartWordResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestService {
    TestStartWordResDto wordTestStart(Member member);
    void saveWordTestScore(WordTestResultReqDto reqDto);
    TestStartWordResDto sentenceTestStart(Member member);
    void saveSentenceTestScore(String index, int score);
    String sttWordTest(String index, MultipartFile sound);
    String sttSentenceTest(SentenceTestSpeakReqDto reqDto) throws IOException;
}
