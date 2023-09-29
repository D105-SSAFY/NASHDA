package com.ssafy.nashda.test.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.*;
import com.ssafy.nashda.test.dto.response.MixTestStartResDto;
import com.ssafy.nashda.test.dto.response.WordTestStartResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestService {
    WordTestStartResDto wordTestStart(Member member);
    void saveWordTestScore(WordTestResultReqDto reqDto, Member member);
    WordTestStartResDto sentenceTestStart(Member member);
    void saveSentenceTestScore(SentenceTestReqDto reqDto, Member member);
    String sttWordTest(String index, MultipartFile sound);
    String sttSentenceTest(SentenceTestSpeakReqDto reqDto) throws IOException;
    MixTestStartResDto mixTestStart(Member member);
    String sttMixTest(WeekTestReqDto reqDto, String type) throws IOException;
    void saveWeekTestSpeed2(WeekTestReqDto reqDtos);
    void saveWeekTestScore(WeekTestResultReqDto reqDto, Member member);
}
