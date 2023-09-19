package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;
import com.ssafy.nashda.token.entity.RefreshToken;
import com.ssafy.nashda.token.repository.RefreshTokenRepository;
import com.ssafy.nashda.token.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final RedisUtil redisUtil;

    @Override
    public TokenResDto createRefreshToken(Member member) {

        // Refresh token 생성, 유효 기간은 7일
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(7));

        // Access token 생성, 유효 기간을 더 짧게 설정
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(1));

        RefreshToken refreshTokenEntity = RefreshToken.builder().member(member).refreshToken(refreshToken).build();

        //mysql에 refresh저장
        refreshTokenRepository.save(refreshTokenEntity);


        // Redis에 저장
        redisUtil.saveAccessToken(member.getEmail(), accessToken);

        System.out.println("key 저장");

        return new TokenResDto(refreshToken, accessToken);

    }

    @Override
    public String createAccessToken(String refreshToken) {

        // refreshtoken으로 user 정보 조회
        String memberEmail = tokenProvider.getUserEmail(refreshToken);

        if (memberEmail == null) {
            return null;
        }

        Member member = memberService.findByEmail(memberEmail);

        if (member == null) {
            return null;
        }
        // accesstoken 생성
        String accessToken = tokenProvider.generateToken(member, Duration.ofDays(1));

        redisUtil.saveAccessToken(member.getEmail(), accessToken);

        return accessToken;
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }

    @Override
    public boolean accessTokenInRedis(String accessToken) {
        String email = tokenProvider.getUserEmail(accessToken);
        String redisAccess = redisUtil.getAccessToken(email);
        return redisAccess != null && redisAccess.equals(accessToken);
    }

    @Override
    public boolean tokenMathchEmail(String token, String email) {
        String emailFromToken = tokenProvider.getUserEmail(token);
        return emailFromToken != null && emailFromToken.equals(email);
    }

}
