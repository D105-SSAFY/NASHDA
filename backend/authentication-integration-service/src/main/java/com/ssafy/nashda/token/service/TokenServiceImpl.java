package com.ssafy.nashda.token.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final RedisUtil redisUtil;


    @Override
    public String createNewAccessToken(String refreshToken) {
// 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("토큰 유효성 실패");
        }

        // refreshToken을 이용해서 user.email을 찾는다
        String email = tokenProvider.getEmailFromToken(refreshToken);
        Member member = memberService.findByEmail(email);

        // 레디스에서 accessToken을 얻는다
        String existingAccessToken = redisUtil.getAccessToken(refreshToken);

        if (existingAccessToken != null) {
            // 기존 accessToken이 있다면, 그것을 반환한다
            return existingAccessToken;
        } else {
            // 기존 accessToken이 없다면, 새로운 것을 생성하고 레디스에 저장한다
            String newAccessToken = tokenProvider.createAccessToken(member);
            redisUtil.setAccessToken(refreshToken, newAccessToken);
            return newAccessToken;
        }

    }
}
