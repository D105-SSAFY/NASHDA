package com.ssafy.nashda.statistic.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.AchievementInfoResDto;
import com.ssafy.nashda.statistic.dto.response.StrickInfoResDto;
import com.ssafy.nashda.statistic.entity.Achievement;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.service.AchievementService;
import com.ssafy.nashda.statistic.service.StrickService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Achievement> achievements = achievementService.getAchievement(member);
        List<AchievementInfoResDto> resDtos = new ArrayList<>();
        for (Achievement achievement : achievements) {
            resDtos.add(new AchievementInfoResDto(achievement));
        }
        return new ResponseEntity<>(new BaseResponseBody(200, "스트릭 전체 조회 성공", resDtos),
                HttpStatus.OK);
    }


}
