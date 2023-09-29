package com.ssafy.nashda.statistic.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.dto.response.MemberStatisticResDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.*;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.service.AchievementService;
import com.ssafy.nashda.statistic.service.GameStatisticService;
import com.ssafy.nashda.statistic.service.StrickService;
import com.ssafy.nashda.statistic.service.WeekTestStatisticService;
import com.ssafy.nashda.week.entity.Week;
import com.ssafy.nashda.week.repository.WeekRepository;
import com.ssafy.nashda.week.service.WeekService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {

    private final StrickService strickService;
    private final AchievementService achievementService;
    private final MemberController memberController;
    private final GameStatisticService gameStatisticService;
    private final GameStatisticService blankStatisticService;
    private final WeekRepository weekRepository;
    private final WeekService weekService;
    private final WeekTestStatisticService weekTestResultService;

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
        List<MemberAchievement> memberAchievements = achievementService.getMemberAchievement(member);

        List<AchievementInfoResDto> resDtos = new ArrayList<>();
        for (MemberAchievement memberAchievement : memberAchievements) {
            resDtos.add(AchievementInfoResDto.fromMemberAchievement(memberAchievement));
        }
        return new ResponseEntity<>(new BaseResponseBody(200, "스트릭 전체 조회 성공", resDtos),
                HttpStatus.OK);
    }

    //게임 통계 최대 5개씩 불러오기, 혹시 몰라서
    @GetMapping("/game")
    public ResponseEntity<? extends BaseResponseBody> getGameStatistic(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
        List<GameStatisticResDto> resDto = gameStatisticService.getGameStatistic(member);
        return new ResponseEntity<>(new BaseResponseBody(200, "게임 통계 조회 성공", resDto),
                HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<? extends BaseResponseBody> getTestStatistic(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
//        List<Object> result = Collections.singletonList(weekTestResultService.getWeekTestResultList(member));
//        MemberStatisticResDto memberStatisticResDto = new MemberStatisticResDto(member);
//        result.add(memberStatisticResDto);

        WeekTestStatisticResDto result = weekTestResultService.getWeekTestResultList(member);

        return new ResponseEntity<>(new BaseResponseBody(200, "테스트 통계 조회 성공", result),
                HttpStatus.OK);
    }

    @GetMapping("/game/blank/{week}")
    public ResponseEntity<? extends BaseResponseBody> getBlankStatistic(@RequestHeader("Authorization") String token, @PathVariable("week") long
            weekIdx) throws Exception {
        Member member = memberController.findMemberByToken(token);
        Week week = weekRepository.findByWeekIdx(weekIdx).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        BlankStatisticResDto blankStatisticResDto = blankStatisticService.getBlankStatistic(member, week);
        return new ResponseEntity<>(new BaseResponseBody(200, "빈칸 게임 통계 조회 성공", blankStatisticResDto),
                HttpStatus.OK);
    }


}
