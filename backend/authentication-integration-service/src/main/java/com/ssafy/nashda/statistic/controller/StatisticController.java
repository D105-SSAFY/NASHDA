package com.ssafy.nashda.statistic.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.AchievementInfoResDto;
import com.ssafy.nashda.statistic.dto.response.StrickInfoResDto;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.service.AchievementService;
import com.ssafy.nashda.statistic.service.GameStatisticService;
import com.ssafy.nashda.statistic.service.StrickService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {

    private final StrickService strickService;
    private final AchievementService achievementService;
    private final MemberController memberController;
    private final GameStatisticService blankStatisticService;

    @GetMapping("/strick")
    public ResponseEntity<? extends BaseResponseBody> getStrick(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
        List<Strick> stricks = strickService.getStrick(member);
        List<StrickInfoResDto> resDtos = new ArrayList<>();
        for (Strick strick : stricks) {
            resDtos.add(new StrickInfoResDto(strick));
        }
        return new ResponseEntity<>(new BaseResponseBody(200, "스트릭 전체 조회 성공", resDtos),
                HttpStatus.OK);
    }

    @GetMapping("/achievement")
    public ResponseEntity<? extends BaseResponseBody> getAchievement(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
        List<MemberAchievement> memberAchievements = achievementService.getAchievement(member);

        List<AchievementInfoResDto> resDtos = new ArrayList<>();
        for (MemberAchievement memberAchievement : memberAchievements) {
            resDtos.add(AchievementInfoResDto.fromMemberAchievement(memberAchievement));
        }
        return new ResponseEntity<>(new BaseResponseBody(200, "스트릭 전체 조회 성공", resDtos),
                HttpStatus.OK);
    }

    //게임 통계 최대 5개씩 불러오기, 혹시 몰라서
    @PostMapping("/game")
    public ResponseEntity<?extends BaseResponseBody> getGameStatistic(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
        return new ResponseEntity<>(new BaseResponseBody(200, "게임 통계 조회 성공"),
                HttpStatus.OK);
    }




}
