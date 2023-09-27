package com.ssafy.nashda.statistic.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.*;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.service.AchievementService;
import com.ssafy.nashda.statistic.service.GameStatisticService;
import com.ssafy.nashda.statistic.service.StrickService;
import com.ssafy.nashda.week.entity.Week;
import com.ssafy.nashda.week.repository.WeekRepository;
import com.ssafy.nashda.week.service.WeekService;
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
    private final GameStatisticService gameStatisticService;
    private final WeekRepository weekRepository;
    private final WeekService weekService;
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
    @GetMapping("/game")
    public ResponseEntity<?extends BaseResponseBody> getGameStatistic(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
        List<GameStatisticResDto> gameStatistics = gameStatisticService.getGameStatistic(member);
        return new ResponseEntity<>(new BaseResponseBody(200, "게임 통계 조회 성공", gameStatistics),
                HttpStatus.OK);
    }

    @GetMapping("/game/blank/{week}")
    public ResponseEntity<?extends BaseResponseBody> getBlankStatistic(@RequestHeader("Authorization") String token, @PathVariable("week") long
            weekIdx) throws Exception {
        Member member = memberController.findMemberByToken(token);
        Week week = weekRepository.findByWeekIdx(weekIdx).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        BlankStatisticResDto blankStatisticResDto = gameStatisticService.getBlankStatistic(member, week);
        return new ResponseEntity<>(new BaseResponseBody(200, "빈칸 게임 통계 조회 성공", blankStatisticResDto),
                HttpStatus.OK);
    }

    @GetMapping("/game/speed/{week}")
    public ResponseEntity<?extends BaseResponseBody> getSpeedStatistic(@RequestHeader("Authorization") String token, @PathVariable("week") long
            weekIdx) throws Exception {
        Member member = memberController.findMemberByToken(token);
        Week week = weekRepository.findByWeekIdx(weekIdx).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
//        SpeedStatisticResDto blankStatisticResDto = blankStatisticService.getBlankStatistic(member, week);
        SpeedStatisticResDto speedStatistic =gameStatisticService.getSpeedStatistic(member, week);
        return new ResponseEntity<>(new BaseResponseBody(200, "스피드 게임 통계 조회 성공", speedStatistic),
                HttpStatus.OK);
    }


}
