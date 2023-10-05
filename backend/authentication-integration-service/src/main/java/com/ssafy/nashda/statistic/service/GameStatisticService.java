package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.BlankStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.GameStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.SpeedStatisticResDto;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.week.entity.Week;

import java.util.List;
import java.util.Optional;

public interface GameStatisticService {
    
    List<GameStatisticResDto> getGameStatistic(Member member);    //게임통계5주치 불러오기
    Optional<GameStatisticResDto> getGameStatisticByWeek(Member member, Week week);   //오늘 주에 해당하는 게임 통계 불러오기
    BlankStatisticResDto getBlankStatistic(Member member, Week week);   //블랭크 게임 통계 불러오기
    SpeedStatisticResDto getSpeedStatistic(Member member, Week week);   //스피드 게임 통계 불러오기
    void initGameStatistic(Member member, Week week);   //게임 통계 초기화
    boolean isExistGameStatistic(Member member, Week week);   //게임 통계가 존재하는지 확인
    void updateBlankScore(Member member, Week week, int score);  //게임 통계 점수  increase
    void updateBlankSet(Member member, Week week);
    void updateBlankTotal(Member member, Week week, int count);

    void updateSpeedScore(Member member, Week week, int score);  //speed 통계 점수  increase
    void updateSpeedSet(Member member, Week week);
    void updateSpeedTotal(Member member, Week week, int count);
}
