package com.ssafy.nashda.token.dto.resonse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class TokenResDto {

    String refreshToken;
    String accessToken;
}
