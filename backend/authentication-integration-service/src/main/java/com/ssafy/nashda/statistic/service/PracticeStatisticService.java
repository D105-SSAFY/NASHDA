package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.CodaStatistic;
import com.ssafy.nashda.statistic.entity.NucleusStatistic;
import com.ssafy.nashda.statistic.entity.OnsetStatistic;

public interface PracticeStatisticService {
    OnsetStatistic findByMemberAndLetter(Member member, String letter) throws Exception;

    OnsetStatistic updateOnsetByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception;
    NucleusStatistic updateNucleusByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception;
    CodaStatistic updateCodaByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception;


    boolean initializePracticeStatistic(Member member) throws Exception;
}
