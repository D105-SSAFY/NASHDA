package com.ssafy.nashda.token.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;
    
    /*
        type
           |- signin : 로그인 시 발송하는 인증번호 저장
           |- token : refreshToken저장
     */


    //이메일 인증 코드 저장
    public void setEmailKey(String email, String code) {
        redisTemplate.opsForHash().put("signin", email, code);
        redisTemplate.expire("signin", 5, TimeUnit.MINUTES);
    }

    //이메일로 인증 코드 조회
    public String getEmailKey(String email) {
        return (String) redisTemplate.opsForHash().get("signin", email);
    }

    //refreshToken저장
    public void setToken(String email, String token) {
        redisTemplate.opsForHash().put("token", email, token);
        redisTemplate.expire("token", 2, TimeUnit.HOURS);
    }

    //이메일로 인증 코드 조회
    public String getToken(String email) {
        return (String) redisTemplate.opsForHash().get("token", email);
    }


    //signin에서 key값을 삭제
    public boolean deleteEmailKey(String key) {
        return redisTemplate.opsForHash().delete("signin", key) > 0;
    }

    //token에서 key값을 삭제
    public boolean deleteToken(String key) {
        return redisTemplate.opsForHash().delete("token", key) > 0;
    }


}