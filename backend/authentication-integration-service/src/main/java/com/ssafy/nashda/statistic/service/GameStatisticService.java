package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.BlankStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.SpeedStatisticResDto;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.week.entity.Week;

import java.util.List;
import java.util.Optional;

public interface GameStatisticService {
    
    List<GameStatistic> getGameStatistic(Member member);    //게임통계5주치 불러오기
    Optional<GameStatistic> getGameStatisticByWeek(Member member, Week week);   //오늘 주에 해당하는 게임 통계 불러오기
    BlankStatisticResDto getBlankStatistic(Member member, Week week);   //블랭크 게임 통계 불러오기
    SpeedStatisticResDto getSpeedStatistic(Member member, Week week);   //스피드 게임 통계 불러오기
}
