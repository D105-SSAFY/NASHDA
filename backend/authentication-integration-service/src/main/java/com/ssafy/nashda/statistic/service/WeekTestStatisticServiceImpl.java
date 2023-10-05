package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.history.dto.HistoryResDto;
import com.ssafy.nashda.history.dto.HistoryStaticticResDto;
import com.ssafy.nashda.history.entity.MemberHistory;
import com.ssafy.nashda.history.service.MemberHistoryService;
//import com.ssafy.nashda.member.dto.response.MemberStatisticResDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.InternalWeekTestStatisticResDto;
import com.ssafy.nashda.statistic.dto.response.WeekTestStatisticResDto;
import com.ssafy.nashda.statistic.entity.WeekTestStatistic;
import com.ssafy.nashda.statistic.repository.WeekTestStatisticRepository;
import com.ssafy.nashda.test.dto.request.WeekTestResultReqDto;
import com.ssafy.nashda.week.entity.Week;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekTestStatisticServiceImpl implements WeekTestStatisticService {

    private final WeekTestStatisticRepository weekTestRepository;
    private final MemberHistoryService memberHistoryService;

    @Override
    public WeekTestStatisticResDto getWeekTestResultList(Member member) {
        //repository에서 뇽 가져온다
        Pageable pageable = PageRequest.of(0, 5);
        List<WeekTestStatistic> weekTestResultList = weekTestRepository.findByMemberOrderByWeekDesc(member, pageable);
        //WeekTestResultResDto 바꿔서 보내준다.
        List<InternalWeekTestStatisticResDto> result = new ArrayList<>();
        for (WeekTestStatistic week : weekTestResultList)
            result.add(new InternalWeekTestStatisticResDto(week));

//        MemberStatisticResDto memberInfo = new MemberStatisticResDto(member);
        HistoryResDto memberHistory = memberHistoryService.getMemberHistory(member);
        HistoryStaticticResDto memberInfo = new HistoryStaticticResDto(memberHistory);

        return new WeekTestStatisticResDto(result,memberInfo);
    }

    @Override
    public void initWeekTestResult(Member member, Week week) {
        WeekTestStatistic weekTestStatistic = WeekTestStatistic.builder()
                .member(member)
                .week(week)
                .build();
        weekTestRepository.save(weekTestStatistic);
    }

    @Override
    public boolean isExistWeekTestResult(Member member, Week week) {
        return weekTestRepository.existsByMemberAndWeek(member, week);
    }

    @Override
    @Transactional
    public void updateWeekTestResult(Member member, Week week, WeekTestResultReqDto reqDto) {
        weekTestRepository.updateScore(member, week, reqDto.getScore(), reqDto.getIndex());
    }
}
