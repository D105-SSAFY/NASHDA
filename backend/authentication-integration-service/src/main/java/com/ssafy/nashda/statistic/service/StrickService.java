package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.week.entity.Week;

import java.util.List;

public interface StrickService {
    List<Strick> getStrick(Member member);
    void initStrick(Member member);
    boolean isExistStrick(Member member);
    void increaseBlankCount(Member member);
    void increasePracticeCount(Member member);
    void increaseSpeedCount(Member member);
    void increaseTestCount(Member member);

}
