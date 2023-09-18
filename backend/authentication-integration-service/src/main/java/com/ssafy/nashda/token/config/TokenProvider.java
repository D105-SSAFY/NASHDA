package com.ssafy.nashda.token.config;

import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.util.RedisUtil;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/*
계속해서 토근을 생성하고 올바른 토큰인지 유효성 검사
토근에서 필요한 정보를 가져오는 클래스
 */


@RequiredArgsConstructor
@Component
public class TokenProvider {

    @Value("${jwt.issuer}")
    private String issuer;    //토큰 발급자는 나!

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    RedisUtil redisUtil;

    //accesstoken만료 시간 30분
    private final long accessTokenValidTime = Duration.ofMinutes(30).toMillis();
    private final long refreshTokenValidTime = Duration.ofHours(2).toMillis();


    //refreshToken 생성
    public String createRefreshToken(Member member) {

        Date now = new Date();

        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)   /*헤더 type : JWT*/
                .setIssuer(issuer)
                .setIssuedAt(now)   /*토큰 iat : 현재 시간*/
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime)) /* 토큰 만료 시간 설정 */
                .claim("email", member.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        redisUtil.setRefreshToken(member.getEmail(), token);

        return token;

    }

    //accessToken생성
    public String createAccessToken(Member member) {
        Date now = new Date();

        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)   /*헤더 type : JWT*/
                .setIssuer(issuer)
                .setIssuedAt(now)   /*토큰 iat : 현재 시간*/
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))  /*토큰 만료 시간 설정*/
                .claim("email", member.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();


        return token;
    }

    // 토큰 유효성 검증
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            // Token has expired
        } catch (UnsupportedJwtException e) {
            // Unsupported JWT token
        } catch (MalformedJwtException e) {
            // Invalid JWT token
        } catch (SignatureException e) {
            // Invalid signature
        } catch (IllegalArgumentException e) {
            // JWT claims string is empty.
        }
        return false;
    }

    //토큰으로 부터 member email을 가져옴
    public String getEmailFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("email", String.class);
    }


    //클레임 조회
    private Claims getClaims(String token) {
        return Jwts.parser() /*클레임 조회*/
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();

    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities)
                , token, authorities);
    }

}
