package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;

import java.util.List;

public interface StrickService {
    List<Strick> getStrick(Member member);
}
