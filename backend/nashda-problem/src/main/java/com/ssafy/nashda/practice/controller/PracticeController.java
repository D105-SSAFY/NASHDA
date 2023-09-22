package com.ssafy.nashda.practice.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.practice.dto.PronSTTResponseDto;
import com.ssafy.nashda.practice.dto.PronSaveRequestDto;
import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import com.ssafy.nashda.practice.service.PracticePronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


//        PronWordSet pronWordSet = practicePronService.savePronWordSet();// Sequence Num 불러오기
//        practicePronService.savePronPhaseSet();
//        practicePronService.savePronSimpleSet();
//        practicePronService.savePronComplexSet();
        log.info("OKOK");
        return new ResponseEntity<>(new BaseResponseBody(200, "소통 테스트 성공22222"), HttpStatus.OK);
    }



    @GetMapping("/pron/nums/{type}")
    public ResponseEntity<? extends BaseResponseBody> getPromSetNum(@PathVariable("type") String type) throws Exception{

        long sequenceNum = practicePronService.getPronSetNum(type);
        log.debug("sequenceNum : {}", sequenceNum);
        return new ResponseEntity<>(new BaseResponseBody(200, "문제 개수 불러오기 성공", sequenceNum),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/word/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronWord(@PathVariable("index") int index) throws Exception {
        PronWordSet pronWordSet = practicePronService.getPronWordSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "단어 문제 불러오기 성공", new PronResponseDto(pronWordSet)),
                HttpStatus.OK);
    }

    @PostMapping("/pron/word/save")
    public ResponseEntity<? extends BaseResponseBody> savePronWord(@RequestBody PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronWordSet pronWordSet = practicePronService.savePronWordSet(pronSaveRequestDto);

        return new ResponseEntity<>(new BaseResponseBody(200, "단어 문제 저장 성공",
                new PronResponseDto(pronWordSet)),
                HttpStatus.OK);
    }



    // 해당 문제 불러오기
    @GetMapping("/pron/phase/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronPhase(@PathVariable("index") int index) throws Exception {
        PronPhaseSet pronPhaseSet = practicePronService.getPronPhaseSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "구 문제 불러오기 성공", new PronResponseDto(pronPhaseSet)),
                HttpStatus.OK);
    }


    @PostMapping("/pron/phase/save")
    public ResponseEntity<? extends BaseResponseBody> savePronPhase(@RequestBody PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronPhaseSet pronPhaseSet = practicePronService.savePronPhaseSet(pronSaveRequestDto);

        return new ResponseEntity<>(new BaseResponseBody(200, "구 문제 저장 성공",
                new PronResponseDto(pronPhaseSet)),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/simple/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronSimple(@PathVariable("index") int index) throws Exception {
        PronSimpleSet pronSimpleSet = practicePronService.getPronSimpleSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "단순절 문제 불러오기 성공", new PronResponseDto(pronSimpleSet)),
                HttpStatus.OK);
    }

    @PostMapping("/pron/simple/save")
    public ResponseEntity<? extends BaseResponseBody> savePronSimple(@RequestBody PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronSimpleSet pronSimpleSet = practicePronService.savePronSimpleSet(pronSaveRequestDto);

        return new ResponseEntity<>(new BaseResponseBody(200, "단순절 문제 저장 성공",
                new PronResponseDto(pronSimpleSet)),
                HttpStatus.OK);
    }

    // 해당 문제 불러오기
    @GetMapping("/pron/complex/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronComplex(@PathVariable("index") int index) throws Exception {
        PronComplexSet pronComplexSet = practicePronService.getPronComplexSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "복합절 문제 불러오기 성공", new PronResponseDto(pronComplexSet)),
                HttpStatus.OK);
    }

    @PostMapping("/pron/complex/save")
    public ResponseEntity<? extends BaseResponseBody> savePronComplex(@RequestBody PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronComplexSet pronComplexSet = practicePronService.savePronComplexSet(pronSaveRequestDto);

        return new ResponseEntity<>(new BaseResponseBody(200, "복합절 문제 저장 성공",
                new PronResponseDto(pronComplexSet)),
                HttpStatus.OK);
    }

//    @PostMapping("/pron/result")
//    public ResponseEntity<? extends BaseResponseBody> getPronunciation(@RequestPart(value="file", required = false) MultipartFile sound,
//                                                                       @RequestBody PracticePronRequestDto practicePronRequestDto) throws Exception {
//
//        String stt = practicePronService.getSTT(sound, practicePronRequestDto.getIndex(), practicePronRequestDto.getType());
//
//        return new ResponseEntity<>(new BaseResponseBody(200, "발음 연습 결과 전송 완료",
//                new PronSTTResponseDto(stt)),
//                HttpStatus.OK);
//    }

}
