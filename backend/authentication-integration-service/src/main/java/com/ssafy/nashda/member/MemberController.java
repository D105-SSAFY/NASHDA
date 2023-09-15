package com.ssafy.nashda.member;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponseBody> insert(
            @RequestBody MemberSignUpReqDto signUpReqDto) throws IOException {
//        Member member = memberController.findMemberByToken(accessToken);

        memberService.signUp(signUpReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201,"회원가입 성공"));
    }

}
