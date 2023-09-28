package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.InternalWeekTestStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.WeekTestStatisticResDto;
import com.ssafy.nashda.test.dto.request.WeekTestResultReqDto;
import com.ssafy.nashda.week.entity.Week;

import java.util.List;

public interface WeekTestStatisticService {
    WeekTestStatisticResDto getWeekTestResultList(Member member);
    void initWeekTestResult(Member member, Week week);
    boolean isExistWeekTestResult(Member member, Week week);
    void updateWeekTestResult(Member member, Week week, WeekTestResultReqDto reqDto);

}
