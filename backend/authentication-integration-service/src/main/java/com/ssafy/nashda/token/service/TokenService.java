package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;

public interface TokenService {

    //refreshToken생성과 동시에 accesstoken생성
    public TokenResDto createRefreshToken(Member member);

    //refreshToken으로 accesstoken생성
    public String createAccessToken(String refreshToken);

    /*해당 email에 대한 refreshtoken을 가져옴*/
/*    public String findRefreshByEmail(String refreshToken);*/

    public boolean tokenMathchEmail(String token, String email);

}
