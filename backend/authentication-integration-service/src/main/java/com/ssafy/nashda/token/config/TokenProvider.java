package com.ssafy.nashda.token.config;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.entity.Member;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

/*
계속해서 토근을 생성하고 올바른 토큰인지 유효성 검사
토근에서 필요한 정보를 가져오는 클래스
 */


@RequiredArgsConstructor
@Service
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date((now.getTime() + expiredAt.toMillis())), member);
    }


    // 토큰 생성
    private String makeToken(Date expiy, Member member) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)   /* JWT의 헤더에 "typ" (토큰의 타입을 나타내는 필드)을 설정 */
                .setIssuer(issuer)   /* issuer(이 토큰을 생성한 서비스)를 설정 */
                .setIssuedAt(now)   /* 토큰이 발행된 시간을 설정 */
                .setExpiration(expiy)   /*  토큰의 만료 시간을 설정 */
                .setSubject(member.getEmail())
                .claim("email", member.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())   /* HS256 알고리즘과 제공된 시크릿 키를 사용하여 JWT에 서명(서명은 JWT의 무결성을 보장하는데 사용) */
                .compact(); /* JWT를 생성하고, 생성된 JWT를 문자열로 직렬화 */
    }

    // 토큰 유효성 검증
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey.getBytes()) // 토큰의 서명을 검증하는 키 설정
                    .parseClaimsJws(token); // 토큰 파싱 및 서명 검증
            return true;
        } //토큰이 비었을 경우 비었다고 exceptio 발생
        catch (IllegalArgumentException e){
            throw new BadRequestException(ErrorCode.TOKEN_NOT_FOUND);
        }catch (Exception e){
            throw new BadRequestException(ErrorCode.TOKEN_DENIED);
        }
    }



    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), "", authorities), token, authorities);
    }

    // 클레임 조회
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get("email", String.class);
    }

}