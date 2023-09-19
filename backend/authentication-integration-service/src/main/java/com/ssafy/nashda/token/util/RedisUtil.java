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

    //refreshToken저장
    public void saveRefreshToken(String email, String token) {
        redisTemplate.opsForHash().put("email:refresh", email, token);
        redisTemplate.expire("email:refresh", 2, TimeUnit.HOURS);
    }

    public void saveAccessToken(String refreshToken, String accessToken) {
        redisTemplate.opsForHash().put("refresh:access", refreshToken, accessToken);
        redisTemplate.expire("refresh:access", 30, TimeUnit.MINUTES);
    }

    //이메일로 인증 코드 조회
    public String getRefreshToken(String email) {
        return (String) redisTemplate.opsForHash().get("email:refresh", email);
    }


    public String getAccessToken(String refreshToken) {
        return (String) redisTemplate.opsForHash().get("refresh:access", refreshToken);
    }

    //signin에서 key값을 삭제
    public boolean deleteCodeKey(String key) {
        return redisTemplate.opsForHash().delete("email:code", key) > 0;
    }

    //token에서 key값을 삭제
    public void deleteRefreshToken(String key) {
        redisTemplate.opsForHash().delete("email:refresh", key);

    }


    public boolean hasAccessToken(String accessToken) {

        // Firstly, get user email from access token
        String userEmail = tokenProvider.getUserEmail(accessToken);
        if(userEmail == null) {
            return false;
        }

        String refreshToken = getRefreshToken(userEmail);
        if(refreshToken == null) {
            return false;
        }



        String retrievedAccessToken = getAccessToken(refreshToken);

        return accessToken.equals(retrievedAccessToken);
    }



}