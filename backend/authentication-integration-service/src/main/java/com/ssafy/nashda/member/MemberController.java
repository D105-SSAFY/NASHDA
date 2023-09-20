package com.ssafy.nashda.member;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;
import com.ssafy.nashda.token.service.TokenService;
import com.ssafy.nashda.token.util.RedisUtil;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final RedisUtil redisUtil;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponseBody> signUp(
            @RequestBody MemberSignUpReqDto signUpReqDto) throws IOException {
        memberService.signUp(signUpReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(200, "회원가입 성공"));
    }
    @GetMapping("/mypage/{nickname}")
    public ResponseEntity<? extends BaseResponseBody> memberInfo(
            @PathVariable String nickname, HttpServletRequest request) throws IOException {

        Optional<Member> member = memberService.findByNickname(nickname);

        if (member.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseBody<>(404, "회원 정보가 없습니다."));
        } else {
           /* //회원 정보가 존재 하기는 하다. 그럼 이제 token검증 시간!
            String token = request.getHeader("Authorization").substring("Bearer ".length()).trim();
            if (!tokenService.tokenMathchEmail(token, member.get().getEmail())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseBody<>(4000, "너랑 맞지 않눈 회원인뒝~~~"));
            }*/


            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "회원 정보 조회 성공", new MemberInfoResDto(member.get())));
        }
    }


    //    @ApiOperation(value = "로그인")
    @PostMapping("/signin")
    public ResponseEntity<? extends BaseResponseBody> logIn(
            @RequestBody MemberSignInReqDto memberSignInReqDto) throws IOException, InterruptedException {

        MemberInfoResDto memberInfo = memberService.singIn(memberSignInReqDto);

        Member member = memberService.findByEmail(memberInfo.getEmail());

        TokenResDto tokens = tokenService.createRefreshToken(member);
        ;
        if (member == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(401, "로그인 실패"));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201, "로그인 성공", tokens));
    }

    @PutMapping("/unregist")
    public ResponseEntity<? extends BaseResponseBody> unRegist(
            @RequestBody MemberSignInReqDto signInReqDto) throws IOException {
        memberService.unRegist(signInReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "회원탈퇴 성공"));
    }

    public Member findMemberByToken(String token) {
        String parsedToken = token.substring("Bearer ".length()).trim();
        String email = tokenProvider.getUserEmail(parsedToken);
        return memberService.findByEmail(email);
    }

}
