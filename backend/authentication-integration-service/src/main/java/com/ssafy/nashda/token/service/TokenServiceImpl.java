package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;
import com.ssafy.nashda.token.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final RedisUtil redisUtil;


    @Override
    public TokenResDto createRefreshToken(Member member) {

// Refresh token 생성, 유효 기간을 더 길게 설정
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(7));

        // Access token 생성, 유효 기간을 더 짧게 설정
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(1));

        // Redis에 저장
        redisUtil.saveRefreshToken(member.getEmail(), refreshToken);
        redisUtil.saveAccessToken(refreshToken, accessToken);

        System.out.println("key 저장");

        return new TokenResDto(refreshToken, accessToken);

    }

    @Override
    public String createAccessToken(String refreshToken) {

        // refreshtoken으로 user 정보 조회
        String memberEmail = tokenProvider.getUserEmail(refreshToken);

        if (memberEmail == null) {
            return null; // or throw an exception
        }

        Member member = memberService.findByEmail(memberEmail);

        if (member == null) {
            return null; // or throw an exception
        }

        // accesstoken 생성
        String accessToken = tokenProvider.generateToken(member, Duration.ofDays(1));

        redisUtil.saveAccessToken(refreshToken, accessToken);

        return accessToken;
    }

    @Override
    public boolean findByRefreshToken(String refreshToken) {

        String refreshInRedis = redisUtil.getRefreshToken(refreshToken);

        return refreshInRedis != null;
    }

}
