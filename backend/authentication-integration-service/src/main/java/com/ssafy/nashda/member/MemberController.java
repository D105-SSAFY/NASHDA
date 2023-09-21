package com.ssafy.nashda.member;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.mail.service.MailSenderService;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.dto.resonse.TokenResDto;
import com.ssafy.nashda.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final MailSenderService mailSenderService;

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponseBody> signUp(
            @RequestBody MemberSignUpReqDto signUpReqDto) throws IOException {
        memberService.signUp(signUpReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(200, "회원가입 성공"));
    }

    @GetMapping("/mypage")
    public ResponseEntity<? extends BaseResponseBody> memberInfo(
            @RequestHeader("Authorization") String token) throws IOException {
        String nickname = findMemberByToken(token).getNickname();
        Optional<Member> member = memberService.findByNickname(nickname);
        if (member.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseBody<>(404, "회원 정보가 없습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "회원 정보 조회 성공", new MemberInfoResDto(member.get())));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<? extends BaseResponseBody> logIn(
            @RequestBody MemberSignInReqDto memberSignInReqDto) throws IOException, InterruptedException {
        MemberInfoResDto memberInfo = memberService.singIn(memberSignInReqDto);
        Member member = memberService.findByEmail(memberInfo.getEmail());
        TokenResDto tokens = tokenService.createRefreshToken(member);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(401, "로그인 실패"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201, "로그인 성공", tokens));
    }

    @PutMapping("/unregist")
    public ResponseEntity<? extends BaseResponseBody> unRegist(@RequestHeader("Authorization") String token,
                                                               @RequestBody Map<String, Object> maps) throws IOException {
        System.out.println(token);
        Member member = findMemberByToken(token); //일단 토큰으로 멤버 찾음
        maps.put("email", member.getEmail());
        memberService.unRegist(maps);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "회원 탈퇴 성공"));

    }

    @PostMapping("/checkemail")
    public ResponseEntity<? extends BaseResponseBody> checkEmail(@RequestBody Map<String, Object> maps) throws IOException {
        if (memberService.checkEmail(maps.get("email").toString()))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "유효한 이메일"));
        else return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(400, "이메일 중복"));
    }

    @PostMapping("/checknickname")
    public ResponseEntity<? extends BaseResponseBody> checkNickName(@RequestBody Map<String, Object> maps) throws IOException {
        if (memberService.checkNickname(maps.get("nickname").toString()))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "유효한 닉네임"));
        else return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(400, "닉네임 중복"));
    }

    @PutMapping("/updateprofile")
    public ResponseEntity<? extends BaseResponseBody> updateProfile(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> maps) throws IOException {
        Member member = findMemberByToken(token);
        maps.put("email", member.getEmail());
        memberService.updateProfile(maps);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "프로필 수정 성공"));
    }

    public ResponseEntity<? extends BaseResponseBody> updatePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> maps) throws IOException {
        Member member = findMemberByToken(token);
        maps.put("email", member.getEmail());
        memberService.updatePassword(maps);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "비밀번호 수정 성공"));
    }


    public Member findMemberByToken(String token) {
        String parsedToken = token.substring("Bearer ".length()).trim();
        String email = tokenProvider.getUserEmail(parsedToken);
        return memberService.findByEmail(email);
    }

    @PutMapping("/resetpw")
    public ResponseEntity<? extends BaseResponseBody> resetPassword(@RequestBody Map<String, Object> map) throws IOException {
        if (map.get("email") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "이메일 입력 필수"));
        if (map.get("newpassword") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "비밀번호 입력 필수"));
        if (map.get("code") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "인증코드 입력 필수"));

        String email = map.get("email").toString();
        String code = map.get("code").toString();
        String newPassword = map.get("newpassword").toString();
        log.info("email : {}", email);
        log.info("code : {}", code);
        log.info("newPassword : {}", newPassword);

        boolean result = mailSenderService.checkCode(email, code);

        if (result) {
            memberService.resetPassword(map);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "비밀번호 변경 성공"));
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "비밀번호 변경 실패"));
    }



}
