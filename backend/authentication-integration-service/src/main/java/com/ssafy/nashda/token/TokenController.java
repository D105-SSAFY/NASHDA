package com.ssafy.nashda.token;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class TokenController {
    private final TokenService tokenService;
    private final TokenProvider tokenProvider;
    private final MemberController memberController;

    @PostMapping("/refresh")
    public ResponseEntity<? extends BaseResponseBody> refreshToken(@RequestHeader("Authorization") String token) throws Exception {
        Member member = memberController.findMemberByToken(token);
        String refreshToken = token.substring("Bearer ".length());
        if (!tokenService.tokenMathchEmail(refreshToken, member.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "refreshtoken만료."));
        }
        String accessToken = tokenService.createAccessToken(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "토큰 재발급 성공", accessToken));
    }

}
