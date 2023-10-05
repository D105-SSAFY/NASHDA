package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;

public interface TokenService {

    TokenResDto createRefreshToken(Member member);
    String createAccessToken(String refreshToken);
    boolean tokenMathchEmail(String token, String email);

}
