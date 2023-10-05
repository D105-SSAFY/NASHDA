package com.ssafy.nashda.token.dto.resonse;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResDto {
    private String refreshToken;
    private String accessToken;

}