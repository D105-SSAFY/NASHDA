package com.ssafy.nashda.practice.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.practice.dto.PronSTTResponseDto;
import com.ssafy.nashda.practice.dto.TestResponseDto;
import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import com.ssafy.nashda.practice.service.PracticePronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 연습 문제 전송 컨트롤러
 * 2023-09-19
 * 조경호
 * */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/practice")
public class PracticeController {
    private final PracticePronService practicePronService;

    
    @GetMapping("/pron/test")
    public ResponseEntity<? extends BaseResponseBody> test() throws Exception {

        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<TestResponseDto> block = client.get()
                .uri("/practice/pron/test")
                .retrieve()
                .toEntity(TestResponseDto.class)
                .block();


        log.info("응답 : {}", block);
        TestResponseDto body = block.getBody();
//        PronWordSet pronWordSet = practicePronService.savePronWordSet();// Sequence Num 불러오기
//        practicePronService.savePronPhaseSet();
//        practicePronService.savePronSimpleSet();
//        practicePronService.savePronComplexSet();

        return new ResponseEntity<>(new BaseResponseBody(200, "소통 테스트 성공", body), HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/word")
    public ResponseEntity<? extends BaseResponseBody> getPronWordNum() throws Exception {

        long pronSetNum = practicePronService.getPronSetNum(PronWordSet.SEQUENCE_NAME); // Sequence Num 불러오기

        return new ResponseEntity<>(new BaseResponseBody(200, "단어 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/word/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronWord(@PathVariable("index") int index) throws Exception {
        PronWordSet pronWordSet = practicePronService.getPronWordSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "단어 문제 불러오기 성공", new PronResponseDto(pronWordSet)),
                HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/phase")
    public ResponseEntity<? extends BaseResponseBody> getPronPhaseNum() throws Exception {

        long pronSetNum = practicePronService.getPronSetNum(PronPhaseSet.SEQUENCE_NAME); // Sequence Num 불러오기

        return new ResponseEntity<>(new BaseResponseBody(200, "구 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/phase/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronPhase(@PathVariable("index") int index) throws Exception {
        PronPhaseSet pronPhaseSet = practicePronService.getPronPhaseSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "구 문제 불러오기 성공", new PronResponseDto(pronPhaseSet)),
                HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/simple")
    public ResponseEntity<? extends BaseResponseBody> getPronSimpleNum() throws Exception {

        long pronSetNum = practicePronService.getPronSetNum(PronSimpleSet.SEQUENCE_NAME); // Sequence Num 불러오기

        return new ResponseEntity<>(new BaseResponseBody(200, "단순절 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/simple/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronSimple(@PathVariable("index") int index) throws Exception {
        PronSimpleSet pronSimpleSet = practicePronService.getPronSimpleSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "단순절 문제 불러오기 성공", new PronResponseDto(pronSimpleSet)),
                HttpStatus.OK);
    }

    // 문제 개수 확인
    @GetMapping("/pron/complex")
    public ResponseEntity<? extends BaseResponseBody> getPronComplexNum() throws Exception {

        long pronSetNum = practicePronService.getPronSetNum(PronComplexSet.SEQUENCE_NAME); // Sequence Num 불러오기

        return new ResponseEntity<>(new BaseResponseBody(200, "복합절 문제 개수 불러오기 성공", pronSetNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/complex/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronComplex(@PathVariable("index") int index) throws Exception {
        PronComplexSet pronComplexSet = practicePronService.getPronComplexSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "복합절 문제 불러오기 성공", new PronResponseDto(pronComplexSet)),
                HttpStatus.OK);
    }

    @PostMapping("/pron/result")
    public ResponseEntity<? extends BaseResponseBody> getPronunciation(@RequestPart(value="file", required = false) MultipartFile sound,
                                                                       @RequestBody PracticePronRequestDto practicePronRequestDto) throws Exception {

        String stt = practicePronService.getSTT(sound, practicePronRequestDto.getIndex(), practicePronRequestDto.getType());

        return new ResponseEntity<>(new BaseResponseBody(200, "발음 연습 결과 전송 완료",
                new PronSTTResponseDto(stt)),
                HttpStatus.OK);
    }

}
