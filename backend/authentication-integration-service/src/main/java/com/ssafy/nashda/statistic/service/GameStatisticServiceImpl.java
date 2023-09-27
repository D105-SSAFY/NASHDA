package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.BlankStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.GameStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.SpeedStatisticResDto;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.statistic.repository.GameStatisticRepository;
import com.ssafy.nashda.week.entity.Week;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameStatisticServiceImpl implements GameStatisticService {

    private final GameStatisticRepository gameStatisticRepository;

    @Override
    public List<GameStatisticResDto> getGameStatistic(Member member) {
        //멤버의 통계 5주치 불러오기
//        return gameStatisticRepository.findTop5ByMemberOrderByWeekDesc(member);
        return gameStatisticRepository.findTop5ByMemberOrderByWeekDesc(member).stream().map(GameStatisticResDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<GameStatisticResDto> getGameStatisticByWeek(Member member, Week week) {
        //오늘 주에 해당하는 게임 통계 불러오기

//        return gameStatisticRepository.findByMemberAndWeek(member, week);
        return gameStatisticRepository.findByMemberAndWeek(member, week).map(GameStatisticResDto::new);
    }

    @Override
    public BlankStatisticResDto getBlankStatistic(Member member, Week week) {
        GameStatistic gameStatistics = gameStatisticRepository.findByMemberAndWeek(member, week).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        return new BlankStatisticResDto(gameStatistics);
    }

    @Override
    public SpeedStatisticResDto getSpeedStatistic(Member member, Week week) {
        GameStatistic gameStatistics = gameStatisticRepository.findByMemberAndWeek(member, week).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        return new SpeedStatisticResDto(gameStatistics);
    }
}