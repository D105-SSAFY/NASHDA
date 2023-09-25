package com.ssafy.nashda.game.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.game.dto.response.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetResponseDto;
import com.ssafy.nashda.game.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final GameService gameService;

    @GetMapping("/speed")
    public ResponseEntity<? extends BaseResponseBody> getSpeedNum() throws Exception {
        long speedSetNum = gameService.getSpeedSetNum();
        return ResponseEntity.ok(new BaseResponseBody(200, "스피드 게임 문제 전송 성공", speedSetNum));
    }

    @GetMapping("/speed/1/{index}")
    public ResponseEntity<? extends BaseResponseBody> getImgWordSet(@PathVariable("index") long index) throws Exception {
        ImgWordSetResponseDto imgWordSet = gameService.getImgWordSet(index);
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", imgWordSet));
    }

    @GetMapping("/speed/2/{index}")
    public ResponseEntity<? extends BaseResponseBody> getImgWordSetList(@PathVariable("index") long index) throws Exception {
        ImgWordSetListResponseDto imgWordSetList = gameService.getImgWordSetList(index);
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공",imgWordSetList));
    }

    @GetMapping("/blank")
    private ResponseEntity<? extends BaseResponseBody> getBlankSetList() throws Exception{
        List<BlankSetResponseDto> blankSetList = gameService.getBlankSetList();

        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", blankSetList));
    }


}
