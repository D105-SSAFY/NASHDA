package com.ssafy.nashda.test.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.*;
import com.ssafy.nashda.test.dto.response.MixTestStartResDto;
import com.ssafy.nashda.test.dto.response.WordTestStartResDto;
import com.ssafy.nashda.test.entity.MixTestResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestService {
    WordTestStartResDto wordTestStart(Member member);
    void saveWordTestScore(WordTestResultReqDto reqDto, Member member);
    WordTestStartResDto sentenceTestStart(Member member);
    void saveSentenceTestScore(SentenceTestReqDto reqDto, Member member);
    String sttWordTest(String index, MultipartFile sound);
    String sttSentenceTest(SentenceTestSpeakReqDto reqDto) throws Exception;
    MixTestStartResDto mixTestStart(Member member);
    String sttMixTest(MixTestSpeekReqDto reqDto, String type) throws Exception;
    void saveWeekTestSpeed2(String index, String url, int order);
    void saveWeekTestScore(WeekTestResultReqDto reqDto, Member member);
}
