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

        // Refresh token 생성, 유효 기간은 7일
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(1));
        // Access token 생성, 유효 기간을 더 짧게 설정
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        //refreshtoken은 redis에 저장한다
        redisUtil.saveRefreshToken(member.getEmail(), refreshToken);

        return new TokenResDto(refreshToken, accessToken);
    }

    @Override
    public String createAccessToken(String refreshToken) {



        //acceestoken이 만료된 상태이다, 여기서 만료가 되었으면? refreshtoken을 확인한다.
        if (redisUtil.isMatchToken(refreshToken)) {
            String email = tokenProvider.getUserEmail(refreshToken);
            Member member = memberService.findByEmail(email);
            return tokenProvider.generateToken(member, Duration.ofHours(2));
        } else {
            throw new IllegalArgumentException("Unexpected token");
        }
    }


    @Override
    public boolean tokenMathchEmail(String token, String email) {
        String emailFromToken = tokenProvider.getUserEmail(token);
        return emailFromToken != null && emailFromToken.equals(email);
    }

}
