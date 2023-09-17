package com.ssafy.nashda.member;

import antlr.Token;
import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.util.RedisUtil;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {

    private final MemberService memberService;
    private final RedisUtil redisUtil;
    private final TokenProvider tokenProvider;


//    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponseBody> signIn(
            @RequestBody MemberSignUpReqDto signUpReqDto) throws IOException {
//        Member member = memberController.findMemberByToken(accessToken);

        Long num = memberService.signUp(signUpReqDto);

        if (num == -1) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(400, "회원가입 실패"));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201, "회원가입 성공"));
    }

//    @ApiOperation(value = "닉네임을 사용해 member정보 조회")
    @GetMapping("/mypage/{nickname}")
    public ResponseEntity<? extends BaseResponseBody> memberInfo(
            @PathVariable String nickname) throws IOException {
        Optional<Member> member = memberService.findByNickname(nickname);

        if (member.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseBody<>(404, "회원 정보가 없습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "회원 정보 조회 성공", member));
        }
    }

//    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponseBody> logIn(
            @RequestBody MemberSignInReqDto memberSignInReqDto) throws IOException, InterruptedException {

        MemberInfoResDto member = memberService.singIn(memberSignInReqDto);

        String refesh = tokenProvider.createRefreshToken(member);
        String access = tokenProvider.createAccessToken(member);


        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201, "로그인 성공", refesh));
    }

//    @ApiOperation(value = "refresh로 access생성")
    @PostMapping("/refresh")
    public ResponseEntity<? extends BaseResponseBody> refreshToken(HttpServletRequest request) {
        System.out.println("?>?");
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String refreshToken = bearerToken.substring("Bearer ".length());
            String newAccessToken = tokenProvider.createNewAccessToken(refreshToken);

            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201, "accesstoken발급 성공", newAccessToken));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(4001, "accesstoken발급 실패"));
    }


}
