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

        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(1));
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        redisUtil.saveRefreshToken(member.getEmail(), refreshToken);

        return new TokenResDto(refreshToken, accessToken);
    }

    @Override
    public String createAccessToken(String refreshToken) {
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
