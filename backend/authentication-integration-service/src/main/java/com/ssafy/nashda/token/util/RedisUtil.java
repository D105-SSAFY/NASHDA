package com.ssafy.nashda.token.util;

import com.ssafy.nashda.token.config.TokenProvider;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor

public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private TokenProvider tokenProvider;

    //이메일 인증 코드 저장
    public void saveEmailKey(String email, String code) {
        redisTemplate.opsForHash().put("email:code", email, code);
        redisTemplate.expire("email:code", 5, TimeUnit.MINUTES);
    }

    //이메일로 인증 코드 조회
    public String getCode(String email) {
        return (String) redisTemplate.opsForHash().get("email:code", email);
    }


    public void saveRefreshToken(String email, String accessToken) {
        redisTemplate.opsForHash().put("email:token", email, accessToken);
        redisTemplate.expire("email:token", 2, TimeUnit.HOURS);
    }

    public String getRefreshTokens(String email) {
        return (String) redisTemplate.opsForHash().get("email:token", email);
    }

    public boolean isMatchToken(String refreshToken){
        //refreshtoken이 해당 유저의 refreshtoken이 맞는지 확인한다.
        String tokenEmail = tokenProvider.getUserEmail(refreshToken);
        String redisToken = getRefreshTokens(tokenEmail);

        if(redisToken!=null && redisToken.equals(redisToken)) return true;
        return false;

    }



}