package com.ssafy.nashda.test.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.service.StrickService;
import com.ssafy.nashda.statistic.service.WeekTestStatisticService;
import com.ssafy.nashda.test.dto.request.*;
import com.ssafy.nashda.test.dto.response.MixTestStartResDto;
import com.ssafy.nashda.test.dto.response.WordTestStartResDto;
import com.ssafy.nashda.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final MemberController memberController;
    private final StrickService strickService;

    @GetMapping("/word")
    public ResponseEntity<? extends BaseResponseBody> wordTestStart(@RequestHeader("Authorization") String token) throws Exception {

        Member member = memberController.findMemberByToken(token);
        WordTestStartResDto wordTestStartResDto = testService.wordTestStart(member);

        return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 불러오기 성공", wordTestStartResDto),
                HttpStatus.OK);
    }

    @PostMapping("/word/result")
    public ResponseEntity<? extends BaseResponseBody> testResult(@RequestHeader("Authorization") String token, @RequestBody WordTestResultReqDto request) throws Exception {

        Member member = memberController.findMemberByToken(token);
        testService.saveWordTestScore(request, member);

        //stick표시를 위한 작업
        strickService.increaseTestCount(member);


        return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 결과 저장 성공"),
                HttpStatus.OK);
    }

    @GetMapping("/sentence")
    public ResponseEntity<? extends BaseResponseBody> sentenceTestStart(@RequestHeader("Authorization") String token) throws Exception {

        Member member = memberController.findMemberByToken(token);
        WordTestStartResDto wordTestStartResDto = testService.sentenceTestStart(member);

        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 불러오기 성공", wordTestStartResDto),
                HttpStatus.OK);
    }

    @PostMapping("/sentence/result")
    public ResponseEntity<? extends BaseResponseBody> sentenceTestResult(@RequestHeader("Authorization") String token, @RequestBody SentenceTestReqDto reqDto) throws Exception {

        Member member = memberController.findMemberByToken(token);
        testService.saveSentenceTestScore(reqDto, member);

        //stick표시를 위한 작업
        strickService.increaseTestCount(member);
        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 결과 저장 성공"),
                HttpStatus.OK);
    }

    @PostMapping(value = "/word/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BaseResponseBody> wordTestUserSpeak(@ModelAttribute WordTestResultSpeakReqDto reqDto) throws Exception {
        String stt = testService.sttWordTest(reqDto);
        return new ResponseEntity<>(new BaseResponseBody(200, "사용자 음성 파일 STT 변환 성공", stt),
                HttpStatus.OK);
    }


    @PostMapping(value = "/sentence/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BaseResponseBody> sentenceTestUserSpeak(
            @ModelAttribute SentenceTestSpeakReqDto reqDto) throws Exception {

        String stt = testService.sttSentenceTest(reqDto);

        return new ResponseEntity<>(new BaseResponseBody(200, "사용자 음성 파일 STT 변환 성공(문장)", stt),
                HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<? extends BaseResponseBody> weekTestStart(@RequestHeader("Authorization") String token) throws Exception {

        Member member = memberController.findMemberByToken(token);
        MixTestStartResDto mixTestStartResDto = testService.mixTestStart(member);

        return new ResponseEntity<>(new BaseResponseBody(200, "통합 시험 불러오기 성공", mixTestStartResDto),
                HttpStatus.OK);
    }

    @PostMapping(value = "/week/user",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BaseResponseBody> weekTestUser(
            @ModelAttribute WeekTestReqDto reqDto) throws Exception {

        if(reqDto.getOrder()<5){
            String stt = testService.sttMixTest(reqDto, "blank");
            return new ResponseEntity<>(new BaseResponseBody(200, "사용자 음성 파일 STT 저장 성공(blank)", stt),
                    HttpStatus.OK);
        }else if(reqDto.getOrder()<8){
            String stt = testService.sttMixTest(reqDto, "speed1");
            return new ResponseEntity<>(new BaseResponseBody(200, "사용자 음성 파일 STT 저장 성공(speed)", stt),
                    HttpStatus.OK);
        }else{
            testService.saveWeekTestSpeed2(reqDto);
            return new ResponseEntity<>(new BaseResponseBody(200, "사용자 선택 저장 성공(speed2)"),
                    HttpStatus.OK);
        }

    }

    @PostMapping("/week/result")
    public ResponseEntity<? extends BaseResponseBody> weekTestResult(@RequestHeader("Authorization") String token, @RequestBody WeekTestResultReqDto reqDto) throws Exception {
        Member member = memberController.findMemberByToken(token);
        testService.saveWeekTestScore(reqDto, member);
        //stick표시를 위한 작업
        strickService.increaseTestCount(member);
        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 결과 저장 성공"),
                HttpStatus.OK);
    }
}
