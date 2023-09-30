package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.history.dto.HistoryStaticticResDto;
//import com.ssafy.nashda.member.dto.response.MemberStatisticResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeekTestStatisticResDto {
    private List<InternalWeekTestStatisticResDto> test_info;
//    private MemberStatisticResDto member_info;
    private HistoryStaticticResDto member_info;
}
