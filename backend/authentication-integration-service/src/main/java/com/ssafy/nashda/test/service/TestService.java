package com.ssafy.nashda.test.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.*;
import com.ssafy.nashda.test.dto.response.MixTestStartResDto;
import com.ssafy.nashda.test.dto.response.WeekTestResultDetailResDto;
import com.ssafy.nashda.test.dto.response.WeekTestResultAllResDto;
import com.ssafy.nashda.test.dto.response.WordTestStartResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestService {
    WordTestStartResDto wordTestStart(Member member);
    void saveWordTestScore(WordTestResultReqDto reqDto, Member member);
    WordTestStartResDto sentenceTestStart(Member member);
    void saveSentenceTestScore(SentenceTestReqDto reqDto, Member member);
    String sttWordTest(MultipartFile sound, WordTestResultSpeakReqDto reqDto) throws Exception;
    String sttSentenceTest(MultipartFile sound, SentenceTestSpeakReqDto reqDto) throws Exception;
    MixTestStartResDto mixTestStart(Member member);
    String sttMixTest(MultipartFile sound, WeekTestReqDto reqDto, String type) throws Exception;
    void saveWeekTestSpeed2(WeekTestReqDto reqDtos);
    void saveWeekTestScore(WeekTestResultReqDto reqDto, Member member);
    WeekTestResultAllResDto getAllWordTestResult(Member member);
    List<WeekTestResultDetailResDto> getWeekTestResultDetail(Member member/*, long week*/);
    int countTestInWeek(Member member);
}
