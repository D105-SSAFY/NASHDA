package com.ssafy.nashda.token.config;

import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.token.util.RedisUtil;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

/*
계속해서 토근을 생성하고 올바른 토큰인지 유효성 검사
토근에서 필요한 정보를 가져오는 클래스
 */


@RequiredArgsConstructor
@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private static String secretKey;

    @Autowired
    RedisUtil redisUtil;

    //accesstoken만료 시간 30분
    private long accessTokenValidTime = Duration.ofHours(2).toMillis();




    //refreshToken 생성
    public String createRefreshToken(MemberInfoResDto member) {
        Claims claims = Jwts.claims().setSubject(member.getEmail());
        Date now = new Date();

        String token = Jwts.builder().setIssuedAt(now) /* 토큰이 발생된 시간 설정 */
                .setSubject(member.getEmail())  /* 토큰이 대상으로 하는 주체 설정 */
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8)) /*  HS256 알고리즘과 제공된 시크릿 키를 사용하여 JWT에 서명 */
                .compact();


        return token;
    }

    //accessToken생성
    public String createAccessToken(MemberInfoResDto member) {
        Claims claims = Jwts.claims().setSubject(member.getEmail());
        Date now = new Date();

        String token = Jwts.builder().setIssuedAt(now) /* 토큰이 발생된 시간 설정 */
                .setSubject(member.getEmail())  /* 토큰이 대상으로 하는 주체 설정 */
                .claim("id", member.getEmail())
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8)) /*  HS256 알고리즘과 제공된 시크릿 키를 사용하여 JWT에 서명 */
                .compact();


        return token;
    }

    //refreshToken을 확인하고 accesstoken재발급
    public String createNewAccessToken(String refreshToken) {
        // 리프레시 토큰에서 이메일 얻기
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(refreshToken)
                    .getBody();
        } catch (SignatureException e) {
            // 서명 검증 실패
            throw new IllegalArgumentException("토큰 서명 검증 실패", e);
        } catch (ExpiredJwtException e) {
            // 토큰이 만료됨
            throw new IllegalArgumentException("토큰이 만료되었습니다.", e);
        } catch (MalformedJwtException e) {
            // 잘못된 토큰 형식
            throw new IllegalArgumentException("잘못된 토큰 형식", e);
        } catch (Exception e) {
            // 기타 오류
            throw new IllegalArgumentException("토큰 처리 중 오류 발생", e);
        }

        String email = claims.getSubject();

        // Redis에서 리프레시 토큰 확인
        String storedToken = redisUtil.getToken(email);
        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        // 새로운 액세스 토큰 생성
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)  // 토큰 대상으로 설정된 이메일을 사용
                .setIssuedAt(now)  // 토큰 발생 시간 설정
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))  // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))  // 서명 알고리즘 및 키 설정
                .compact();
    }


    // 토큰 유효성 검증
    public boolean validToken(String token) {
        try{
            Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)) /* 비밀값으로 복호화 */
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //토큰으로부터 사용자 이름 가져오기
    public String getUserEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            throw new IllegalArgumentException("토큰에서 사용자 이름을 가져올 수 없습니다.", e);
        }
    }


}
