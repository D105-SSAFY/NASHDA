package com.ssafy.nashda.test.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.WordTestResultReqDto;
import com.ssafy.nashda.test.dto.response.TestStartWordResDto;
import com.ssafy.nashda.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final MemberController memberController;

    @GetMapping("/word")
    public ResponseEntity<? extends BaseResponseBody> wordTestStart(@RequestHeader("Authorization") String token) throws Exception{

        Member member = memberController.findMemberByToken(token);
        TestStartWordResDto wordTestStartResDto = testService.wordTestStart(member);

      /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 불러오기 성공", wordTestStartResDto),
                HttpStatus.OK);
    }

    @PostMapping("/word/result")
    public ResponseEntity<? extends BaseResponseBody> testResult(@RequestBody WordTestResultReqDto request) throws Exception{

        testService.saveWordTestScore(request);

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 결과 저장 성공"),
                HttpStatus.OK);
    }

    @GetMapping("/sentence")
    public ResponseEntity<? extends BaseResponseBody> sentenceTestStart(@RequestHeader("Authorization") String token) throws Exception{

        Member member = memberController.findMemberByToken(token);
        TestStartWordResDto wordTestStartResDto = testService.sentenceTestStart(member);

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 불러오기 성공",wordTestStartResDto ),
                HttpStatus.OK);
    }

    @PostMapping("/sentence/result")
    public ResponseEntity<? extends BaseResponseBody> sentenceTestResult(@RequestBody Map<String, Object> map) throws Exception{

        testService.saveSentenceTestScore((String) map.get("index"), (Integer) map.get("score"));

        /*  return new ResponseEntity<>(new BaseResponseBody(200, "단어 시험 시작"), wordTestStartResDto), HttpStatus.OK);*/
        return new ResponseEntity<>(new BaseResponseBody(200, "문장 시험 결과 저장 성공"),
                HttpStatus.OK);
    }




}
