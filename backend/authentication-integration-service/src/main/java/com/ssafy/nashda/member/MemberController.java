package com.ssafy.nashda.member;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;


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

    @GetMapping("/mypage/{nickname}")
    public ResponseEntity<? extends BaseResponseBody> memberInfo(
            @PathVariable String nickname) throws IOException {
        Optional<Member> member = memberService.findByNickname(nickname);

        if (member.isEmpty()) {
            // 회원 정보가 존재하면, 회원 정보를 응답 본문에 추가
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseBody<>(404, "회원 정보가 없습니다."));
        } else {
            // 회원 정보가 없으면, 적절한 오류 메시지를 반환
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "회원 정보 조회 성공", member));
        }
    }




}
