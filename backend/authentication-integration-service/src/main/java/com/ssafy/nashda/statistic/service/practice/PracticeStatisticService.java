package com.ssafy.nashda.statistic.service.practice;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.practice.CodaStatistic;
import com.ssafy.nashda.statistic.entity.practice.NucleusStatistic;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;

public interface PracticeStatisticService {
    OnsetStatistic findByMemberAndLetter(Member member, String letter) throws Exception;

    OnsetStatistic updateOnsetByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception;
    NucleusStatistic updateNucleusByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception;
    CodaStatistic updateCodaByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception;


    boolean initializePracticeStatistic(Member member) throws Exception;
}
