package com.ssafy.nashda.statistic.service.practice;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;

public interface PracticeStatisticService {
    OnsetStatistic findByMemberAndLetter(Member member, String letter) throws Exception;

    OnsetStatistic updateByMemberAndLetter(Member member, String letter) throws Exception;

}
