package com.ssafy.nashda.token;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity<? extends BaseResponseBody> refreshToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String refreshToken = bearerToken.substring("Bearer ".length());
            try {
                // refreshtoken을 이용해서 accesstoken새로 생성
                String accessToken = tokenService.createNewAccessToken(refreshToken);

                return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(2000, accessToken));
            } catch (Exception e) {
                log.error("Token refresh failed: ", e);  // 로그에 예외 메시지와 스택 추적을 기록
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(4001, "accesstoken발급 실패: " + e.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(4000, "올바른 토큰이 아닙니다."));
        }
    }
}
