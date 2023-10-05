package com.ssafy.nashda.game.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.game.dto.request.*;
import com.ssafy.nashda.game.dto.response.*;
import com.ssafy.nashda.game.service.GameService;
import com.ssafy.nashda.history.service.MemberHistoryService;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.service.StrickService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final GameService gameService;
    private final MemberController memberController;
    private final StrickService strickService;
    private final MemberHistoryService memberHistoryService;

    @GetMapping("/speed")
    public ResponseEntity<? extends BaseResponseBody> getSpeedNum() throws Exception {
        long speedSetNum = gameService.getSpeedSetNum();
        return ResponseEntity.ok(new BaseResponseBody(200, "스피드 게임 문제 전송 성공", speedSetNum));
    }

    @GetMapping("/speed/1/{index}")
    public ResponseEntity<? extends BaseResponseBody> getImgWordSet(@RequestHeader("Authorization") String token, @PathVariable("index") long index) throws Exception {
        ImgWordSetResponseDto imgWordSet = gameService.getImgWordSet(index);
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", imgWordSet));
    }

    @GetMapping("/speed/2/{index}")
    public ResponseEntity<? extends BaseResponseBody> getImgWordSetList(@RequestHeader("Authorization") String token, @PathVariable("index") long index) throws Exception {
        ImgWordSetListResponseDto imgWordSetList = gameService.getImgWordSetList(index);
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", imgWordSetList));
    }

    @GetMapping("/blank")
    private ResponseEntity<? extends BaseResponseBody> getBlankSetList(@RequestHeader("Authorization") String token) throws Exception {
        List<BlankSetResponseDto> blankSetList = gameService.getBlankSetList();
        Member member = memberController.findMemberByToken(token);
        memberHistoryService.increaseGameBlankCount(member);
        return ResponseEntity.ok(new BaseResponseBody(200, "문제 불러오기 성공", blankSetList));
    }

    @PostMapping("")
    public ResponseEntity<? extends BaseResponseBody> getSTT(@RequestPart(value = "sound") MultipartFile sound,
                                                            @ModelAttribute GameSTTReqDto gameSTTReqDto) throws Exception {
        return ResponseEntity.ok(new BaseResponseBody(200, "채점 결과", gameService.convertSTT(sound, gameSTTReqDto)));

    }

    @PostMapping("/speed/result")
    public ResponseEntity<? extends BaseResponseBody> saveSpeedResult(@RequestHeader("Authorization") String token, @RequestBody SpeedResultReqDto request) throws Exception {
        Member member = memberController.findMemberByToken(token);


        gameService.saveSpeedResult(request, member);
        strickService.increaseSpeedCount(member);
        memberHistoryService.plusWordCount(member, request.getTotal());
        memberHistoryService.increaseGameSpeedCount(member);
        return ResponseEntity.ok(new BaseResponseBody(200, "스피드 게임 결과 저장 성공"));
    }

    @PostMapping("/blank/result")
    public ResponseEntity<? extends BaseResponseBody> saveBlankResult(@RequestHeader("Authorization") String token, @RequestBody BlankResultReqDto request) throws Exception {
        Member member = memberController.findMemberByToken(token);
        //strick에 표시할 것
        strickService.increaseBlankCount(member);
        memberHistoryService.increaseGameBlankCount(member);
        memberHistoryService.plusSentenceCount(member, request.getTotal());

        int progress = gameService.saveBlankResult(request, member);
        Map<String, Integer> result = new HashMap<>();
        result.put("progress", progress);
        return ResponseEntity.ok(new BaseResponseBody(200, "빈칸 게임 결과 저장 성공", result));
    }

    @PostMapping("/img-word/save")
    public ResponseEntity<? extends BaseResponseBody> saveImgWordSet(@ModelAttribute ImgWordSetSaveReqDto imgWordSetSaveReqDto) throws Exception {
        ImgWordSetResponseDto imgWordSetResponseDto = gameService.saveImgWordSet(imgWordSetSaveReqDto);

        return ResponseEntity.ok(new BaseResponseBody(200, "단어-이미지 문제 저장 성공", imgWordSetResponseDto));
    }

    @PostMapping(path = "/blank/save")
    public ResponseEntity<? extends BaseResponseBody> saveBlankSet(@ModelAttribute BlankSetSaveReqDto blankSetSaveReqDto) throws Exception {
        BlankSetResponseDto blankSetResponseDto = gameService.saveBlankSet(blankSetSaveReqDto);

        return ResponseEntity.ok(new BaseResponseBody(200, "빈칸 게임 문제 저장 성공", blankSetResponseDto));
    }


}
