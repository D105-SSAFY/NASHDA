package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;

public interface TokenService {

    //refreshToken생성
    public TokenResDto createRefreshToken(Member member);

    //accessToken생성
    public String createAccessToken(String refreshToken);

    //refresh토큰으로 accesstoken조회
    public boolean findByRefreshToken(String refreshToken);

    //해당 refreshToken이 redis에 존재하는지 확인
    

}
