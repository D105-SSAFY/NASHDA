package com.ssafy.nashda.game.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.game.dto.*;
import com.ssafy.nashda.game.entity.BlankQuestionSet;
import com.ssafy.nashda.game.entity.ImgWordSet;
import com.ssafy.nashda.game.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @PostMapping("/img-word/save")
    public ResponseEntity<? extends BaseResponseBody> saveImgWordSet(@RequestBody ImgWordSetSaveRequestDto requestDto) throws Exception {
        ImgWordSet imgWordSet = gameService.saveImgWordSet(requestDto);

        return ResponseEntity.ok(new BaseResponseBody(200, "문제 저장 성공",
                new ImgWordSetResponseDto(imgWordSet)));
    }

    @GetMapping("/speed")
    public ResponseEntity<? extends BaseResponseBody> getSpeedNum() throws Exception {
        long gameSetNum = gameService.getGameSetNum(ImgWordSet.SEQUENCE_NAME);
        return ResponseEntity.ok(new BaseResponseBody(200, "스피드 게임 문제 개수 전송 성공", gameSetNum));
    }

    @GetMapping("/speed/1/{index}")
    public ResponseEntity<? extends BaseResponseBody> getImgWordSet(@PathVariable("index") long index) throws Exception {
        ImgWordSet imgWordSet = gameService.getImgWordSet(index);
        ImgWordSetResponseDto imgWordSetResponseDto = new ImgWordSetResponseDto(imgWordSet);
        imgWordSetResponseDto.setType(1);
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", imgWordSetResponseDto));
    }

    @GetMapping("/speed/2/{index}")
    public ResponseEntity<? extends BaseResponseBody> getImgWordSetList(@PathVariable("index") long index) throws Exception {
        List<ImgWordSet> imgWordSetList = gameService.getImgWordSetList(index);

        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공",
                new ImgWordSetListResponseDto(imgWordSetList)));
    }


    @PostMapping ("/blank/save")
    public ResponseEntity<? extends BaseResponseBody> saveBlankSet(@RequestBody BlankSetSaveRequestDto requestDto) throws Exception {
        BlankQuestionSet blankQuestionSet = gameService.saveBlankSet(requestDto);

        return ResponseEntity.ok(new BaseResponseBody(200, "빈칸 문제 저장 성공",
                new BlankSetResponseDto(blankQuestionSet)));
    }
    @GetMapping("/blank")
    private ResponseEntity<? extends BaseResponseBody> getBlankSetList() throws Exception{
        List<BlankQuestionSet> blankSetList = gameService.getBlankSetList();
        List<BlankSetResponseDto> responseDtoList = new ArrayList<>();

        for(BlankQuestionSet blankQuestionSet : blankSetList){
            responseDtoList.add(new BlankSetResponseDto(blankQuestionSet));
        }

        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", responseDtoList));
    }
}
