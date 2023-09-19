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

/*    //refreshToken저장
    public void saveRefreshToken(String email, String token) {
        redisTemplate.opsForHash().put("email:refresh", email, token);
        redisTemplate.expire("email:refresh", 2, TimeUnit.HOURS);
    }*/

    public void saveAccessToken(String email, String accessToken) {
        redisTemplate.opsForHash().put("email:access", email, accessToken);
        redisTemplate.expire("email:access", 30, TimeUnit.MINUTES);
    }

    public String getAccessToken(String email) {
        return (String) redisTemplate.opsForHash().get("email:access", email);
    }

    public boolean hasAccessToken(String accessToken) {

        //accesstoken에서 email을 추출해서 해당 redis set이 있는지를 확인한다.
        String userEmail = tokenProvider.getUserEmail(accessToken);

        if (userEmail == null || userEmail.isEmpty()) {
            return false;
        }

        String redisAccessToken = getAccessToken(userEmail);

        return redisAccessToken.equals(accessToken);
    }


}