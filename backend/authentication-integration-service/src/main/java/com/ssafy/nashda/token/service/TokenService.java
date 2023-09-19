package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;
import com.ssafy.nashda.token.entity.RefreshToken;

public interface TokenService {

    //refreshToken생성과 동시에 accesstoken생성
    public TokenResDto createRefreshToken(Member member);

    //refreshToken으로 accesstoken생성
    public String createAccessToken(String refreshToken);

    //mysql에 대항 RefreshToken이 존재하는지 확인.
    public RefreshToken findByRefreshToken(String refreshToken);

    //해당 accesstoken이 redis에 존재하는지 확인
    public boolean accessTokenInRedis(String accessToken);

    public boolean tokenMathchEmail(String token, String email);

}
