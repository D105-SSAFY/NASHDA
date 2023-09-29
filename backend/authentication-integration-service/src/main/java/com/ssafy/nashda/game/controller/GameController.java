package com.ssafy.nashda.game.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.game.dto.request.*;
import com.ssafy.nashda.game.dto.response.*;
import com.ssafy.nashda.game.service.GameService;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.statistic.service.AchievementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final GameService gameService;
    private final MemberController memberController;

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

    @PostMapping("/")
    public ResponseEntity<?extends BaseResponseBody> getSTT(@ModelAttribute GameSTTReqDto gameSTTReqDto ) throws Exception {
//       return ResponseEntity.ok(new BaseResponseBody(200, "채점 결과", gameService.convertSTT(gameSTTReqDto, sound)));
        return ResponseEntity.ok(new BaseResponseBody(200, "채점 결과", gameService.convertSTT(gameSTTReqDto)));

    }

    @PostMapping("/speed/result")
    public ResponseEntity<? extends BaseResponseBody> saveSpeedResult(@RequestHeader("Authorization") String token, @RequestBody SpeedResultReqDto request) throws Exception {
        Member member = memberController.findMemberByToken(token);
        gameService.saveSpeedResult(request, member);
        return ResponseEntity.ok(new BaseResponseBody(200, "스피드 게임 결과 저장 성공"));
    }

    @PostMapping("/blank/result")
    public ResponseEntity<? extends BaseResponseBody> saveBlankResult(@RequestHeader("Authorization") String token, @RequestBody BlankResultReqDto request) throws Exception {
        Member member = memberController.findMemberByToken(token);
        gameService.saveBlankResult(request, member);
        return ResponseEntity.ok(new BaseResponseBody(200, "빈칸 게임 결과 저장 성공"));
    }

    @PostMapping("/img-word/save")
    public ResponseEntity<? extends BaseResponseBody> saveImgWordSet(@ModelAttribute ImgWordSetSaveReqDto imgWordSetSaveReqDto) throws Exception {
        ImgWordSetResponseDto imgWordSetResponseDto = gameService.saveImgWordSet(imgWordSetSaveReqDto);

        return ResponseEntity.ok(new BaseResponseBody(200, "단어-이미지 문제 저장 성공",imgWordSetResponseDto));
    }

    @PostMapping(path = "/blank/save")
    public ResponseEntity<? extends BaseResponseBody> saveBlankSet(@ModelAttribute BlankSetSaveReqDto blankSetSaveReqDto) throws Exception {
        BlankSetResponseDto blankSetResponseDto = gameService.saveBlankSet(blankSetSaveReqDto);

        return ResponseEntity.ok(new BaseResponseBody(200, "빈칸 게임 문제 저장 성공", blankSetResponseDto));
    }


}
