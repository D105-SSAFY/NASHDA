package com.ssafy.nashda.practice.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.practice.entity.PronWordSet;
import com.ssafy.nashda.practice.service.PracticePronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/practice")
public class PronController {
    private final PracticePronService practicePronService;

    @GetMapping("/pron/word")
    public ResponseEntity<? extends BaseResponseBody> savePronWord() throws Exception {
        PronWordSet pronunciationWordSet = practicePronService.savePronWordSet();

        return new ResponseEntity<>(new BaseResponseBody(200, "발음 워드 저장 성공!",
                new PronResponseDto(pronunciationWordSet)),
                HttpStatus.OK);
    }

    @PostMapping("/pron/word/{index}")
    public ResponseEntity<? extends BaseResponseBody> getPronWord(@PathVariable("index") int index, @RequestBody PracticePronRequestDto practicePronRequestDto) throws Exception{
        PronWordSet pronWorkSet = practicePronService.getPronWordSets(index);

        return new ResponseEntity<>(new BaseResponseBody(200, "문제 조회 성공", new PronResponseDto(pronWorkSet)),
                HttpStatus.OK);
    }
}
