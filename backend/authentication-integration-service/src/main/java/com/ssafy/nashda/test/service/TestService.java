package com.ssafy.nashda.test.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.MixTestSpeekReqDto;
import com.ssafy.nashda.test.dto.request.SentenceTestSpeakReqDto;
import com.ssafy.nashda.test.dto.request.WordTestResultReqDto;
import com.ssafy.nashda.test.dto.response.MixTestStartResDto;
import com.ssafy.nashda.test.dto.response.WordTestStartResDto;
import com.ssafy.nashda.test.entity.MixTestResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestService {
    WordTestStartResDto wordTestStart(Member member);
    void saveWordTestScore(WordTestResultReqDto reqDto);
    WordTestStartResDto sentenceTestStart(Member member);
    void saveSentenceTestScore(String index, int score);
    String sttWordTest(String index, MultipartFile sound);
    String sttSentenceTest(SentenceTestSpeakReqDto reqDto) throws IOException;
    MixTestStartResDto mixTestStart(Member member);
    String sttMixTest(MixTestSpeekReqDto reqDto, String type) throws IOException;
//    String sttWeekTestBlank(MixTestSpeekReqDto reqDto) throws IOException;

    void saveWeekTestScore(String index, int score);
}
