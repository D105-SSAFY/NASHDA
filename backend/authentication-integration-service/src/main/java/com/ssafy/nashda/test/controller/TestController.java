package com.ssafy.nashda.test.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.SentenceTestSpeakReqDto;
import com.ssafy.nashda.test.dto.request.WordTestResultReqDto;
import com.ssafy.nashda.test.dto.response.TestStartWordResDto;
import com.ssafy.nashda.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final MemberController memberController;

    @GetMapping("/word")
    public ResponseEntity<? extends BaseResponseBody> wordTestStart(@RequestHeader("Authorization") String token) throws Exception {

        Member member = memberController.findMemberByToken(token);
        TestStartWordResDto wordTestStartResDto = testService.wordTestStart(member);

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 불러오기 성공", wordTestStartResDto),
                HttpStatus.OK);
    }

    @PostMapping("/word/result")
    public ResponseEntity<? extends BaseResponseBody> testResult(@RequestBody WordTestResultReqDto request) throws Exception {

        testService.saveWordTestScore(request);

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 결과 저장 성공"),
                HttpStatus.OK);
    }

    @GetMapping("/sentence")
    public ResponseEntity<? extends BaseResponseBody> sentenceTestStart(@RequestHeader("Authorization") String token) throws Exception {

        Member member = memberController.findMemberByToken(token);
        TestStartWordResDto wordTestStartResDto = testService.sentenceTestStart(member);

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 불러오기 성공", wordTestStartResDto),
                HttpStatus.OK);
    }

    @PostMapping("/sentence/result")
    public ResponseEntity<? extends BaseResponseBody> sentenceTestResult(@RequestBody Map<String, Object> map) throws Exception {

        testService.saveSentenceTestScore((String) map.get("index"), (Integer) map.get("score"));

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 결과 저장 성공"),
                HttpStatus.OK);
    }

    @PostMapping(value = "/word/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BaseResponseBody> wordTestUserSpeak(@RequestParam("index") String index,
                                                                        @RequestParam("sound") MultipartFile sound) throws Exception {

        String stt = testService.sttWordTest(index, sound);

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "사용자 음성 파일 STT 변환 성공", stt),
                HttpStatus.OK);
    }


    @PostMapping(value = "/sentence/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BaseResponseBody> sentenceTestUserSpeak(
            @RequestParam("index") String index, @RequestParam("sound") MultipartFile sound, @RequestParam("order")int order
/*    @ModelAttribute SentenceTestSpeakReqDto reqDto*/) throws Exception {

        SentenceTestSpeakReqDto reqDto = SentenceTestSpeakReqDto.builder()
                .index(index)
                .order(order)
                .sound(sound)
                .build();
        String stt = testService.sttSentenceTest(reqDto);

        return new ResponseEntity<>(new BaseResponseBody(200, "사용자 음성 파일 STT 변환 성공(문장)", stt),
                HttpStatus.OK);
    }
}