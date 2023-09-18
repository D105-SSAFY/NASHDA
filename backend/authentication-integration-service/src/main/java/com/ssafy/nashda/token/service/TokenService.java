package com.ssafy.nashda.token.service;

public interface TokenService {

    //refreshtoken을 전달받고 해당 토큰의 유효성을 검증 한 뒤 accesstoken을 생성해서 준다.
    public String createNewAccessToken(String refreshToken);

}
