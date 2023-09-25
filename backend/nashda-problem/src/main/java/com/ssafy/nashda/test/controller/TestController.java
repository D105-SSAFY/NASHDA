package com.ssafy.nashda.test.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.game.dto.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.ImgWordSetResponseDto;
import com.ssafy.nashda.game.entity.ImgWordHint;
import com.ssafy.nashda.game.entity.ImgWordSet;
import com.ssafy.nashda.game.service.GameService;
import com.ssafy.nashda.test.dto.response.Speed1TestResDto;
import com.ssafy.nashda.test.dto.response.Speed2TestResDto;
import com.ssafy.nashda.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final GameService gameService;

    @GetMapping("/speed1/random")
    public ResponseEntity<? extends BaseResponseBody> getSpeed1Sets() throws Exception {

        List<ImgWordSet> speed1List = testService.getSpeed1Sets();

//        ImgWordSetResponseDto imgWordSetResponseDto = new ImgWordSetResponseDto(imgWordSet);

        List<Speed1TestResDto> list = new ArrayList<>();
        for(ImgWordSet img : speed1List){
            Speed1TestResDto dto = new Speed1TestResDto(img);
            list.add(dto);
        }
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 저장 성공",
                list));
    }

    @GetMapping("/speed2/random")
    public ResponseEntity<? extends BaseResponseBody> getSpeed2Sets()throws Exception{
        List<ImgWordSetListResponseDto> list = new ArrayList<>();

        List<Integer> randomNum = testService.generateRandomNum();

//        List<ImgWordSet> imgWordSetList = gameService.getImgWordSetList(index);
        for(int index : randomNum){
            List<ImgWordSet> set =gameService.getImgWordSetList(index);
            list.add(new ImgWordSetListResponseDto(set));
        }

        return ResponseEntity.ok(new BaseResponseBody(200, "speed2 문제 불러오기 성공",
                list));
    }

}
