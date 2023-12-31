package com.ssafy.nashda.practice.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.history.service.MemberHistoryService;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.practice.dto.PronSTTResponseDto;
import com.ssafy.nashda.practice.service.PracticePronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 연습 문제 전송 컨트롤러
 * 2023-09-19
 * 조경호
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/practice")
public class PracticeController {
    private final PracticePronService practicePronService;
    private final MemberController memberController;
    private final MemberHistoryService memberHistoryService;

    // 문제 개수 확인
    @GetMapping("/pron/word")
    public ResponseEntity<? extends BaseResponseBody> getPronWordNum() throws Exception {
        long pronSetNum = practicePronService.getPronSetNum("pronunciation_word_sequence"); // Sequence Num 불러오기
        return new ResponseEntity<>(new BaseResponseBody(200, "단어 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/word/{index}")
    @Transactional
    public ResponseEntity<? extends BaseResponseBody> getPronWord(@RequestHeader("Authorization") String token, @PathVariable("index") int index) throws Exception {
        PronResponseDto pronWordSet = practicePronService.getPronWordSets(index);
        Member member = memberController.findMemberByToken(token);
        memberHistoryService.increaseWordCount(member); //wordcount 증가
        memberHistoryService.increasePracticeWordCount(member); //practice를 통한 wordcount 증가
        return new ResponseEntity<>(new BaseResponseBody(200, "단어 문제 불러오기 성공", pronWordSet),
                HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/phase")
    public ResponseEntity<? extends BaseResponseBody> getPronPhaseNum() throws Exception {
        long pronSetNum = practicePronService.getPronSetNum("pronunciation_phase_sequence"); // Sequence Num 불러오기
        return new ResponseEntity<>(new BaseResponseBody(200, "구 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/phase/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronPhase(@RequestHeader("Authorization") String token, @PathVariable("index") int index) throws Exception {
        PronResponseDto pronPhaseSet = practicePronService.getPronPhaseSets(index);
        Member member = memberController.findMemberByToken(token);
        memberHistoryService.increaseWordCount(member); //wordcount 증가
        memberHistoryService.increasePracticeWordCount(member); //practice를 통한 wordcount 증가
        return new ResponseEntity<>(new BaseResponseBody(200, "구 문제 불러오기 성공", pronPhaseSet),
                HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/simple")
    public ResponseEntity<? extends BaseResponseBody> getPronSimpleNum() throws Exception {

        long pronSetNum = practicePronService.getPronSetNum("pronunciation_simple_sequence"); // Sequence Num 불러오기
        return new ResponseEntity<>(new BaseResponseBody(200, "단순절 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/simple/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronSimple(@RequestHeader("Authorization") String token, @PathVariable("index") int index) throws Exception {
        PronResponseDto pronSimpleSet = practicePronService.getPronSimpleSets(index);
        Member member = memberController.findMemberByToken(token);
        memberHistoryService.increaseSentenceCount(member); //문장 count 증가
        memberHistoryService.increasePracticeSentenceCount(member); //practice를 통한 문장 count 증가
        return new ResponseEntity<>(new BaseResponseBody(200, "단순절 문제 불러오기 성공", pronSimpleSet),
                HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/complex")
    public ResponseEntity<? extends BaseResponseBody> getPronComplexNum() throws Exception {

        long pronSetNum = practicePronService.getPronSetNum("pronunciation_complex_sequence"); // Sequence Num 불러오기
        return new ResponseEntity<>(new BaseResponseBody(200, "복합절 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/complex/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronComplex(@RequestHeader("Authorization") String token, @PathVariable("index") int index) throws Exception {
        PronResponseDto pronComplexSet = practicePronService.getPronComplexSets(index);
        Member member = memberController.findMemberByToken(token);
        memberHistoryService.increaseSentenceCount(member); //문장 count 증가
        memberHistoryService.increasePracticeSentenceCount(member); //practice를 통한 문장 count 증가
        return new ResponseEntity<>(new BaseResponseBody(200, "복합절 문제 불러오기 성공", pronComplexSet),
                HttpStatus.OK);
    }

    @PostMapping(value = "/pron/result")
    public ResponseEntity<? extends BaseResponseBody> getPronunciation(@RequestPart(value = "sound") MultipartFile sound,
                                                                        @ModelAttribute PracticePronRequestDto practicePronRequestDto,
                                                                       @RequestHeader("Authorization") String token) throws Exception {

        Member member = memberController.findMemberByToken(token);
        PronSTTResponseDto pronSTTResponseDto = practicePronService.getPracSTT(member, sound, practicePronRequestDto);
        memberHistoryService.increasePracticeCount(member);
        return new ResponseEntity<>(new BaseResponseBody(200, "발음 연습 결과 전송 완료",
                pronSTTResponseDto),
                HttpStatus.OK);
    }

}
