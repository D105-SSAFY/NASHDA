package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;

public interface TokenService {

    public TokenResDto createRefreshToken(Member member);
    public String createAccessToken(String refreshToken);
    public boolean tokenMathchEmail(String token, String email);

}
