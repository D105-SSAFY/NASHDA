package com.ssafy.nashda.member.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.repository.MemberRepository;
import com.ssafy.nashda.token.config.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service("MemberService")
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public void signUp(MemberSignUpReqDto signUpReqDto) {
        //데이터베이스에서 해당 사용자와 동일한 email을 가진 유저가 있는지 검색한다.
        Optional<Member> exitMember = memberRepository.findByEmail(signUpReqDto.getEmail());
        //비어있다면? 중복된 회원이 없다는 것 -> 회원가입 가능
        if (exitMember.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(signUpReqDto.getPassword());
            Member member = new Member(signUpReqDto.getEmail(),
                    signUpReqDto.getName(),
                    signUpReqDto.getNickname(),
                    encodedPassword, // 이미 인코딩된 비밀번호를 사용
                    signUpReqDto.getAge(),
                    signUpReqDto.getHobbyIdx(),
                    signUpReqDto.getJobIdx());
            memberRepository.save(member);
        } else {
            throw new BadRequestException(ErrorCode.USER_EXIST);
        }
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return Optional.ofNullable(memberRepository.findByNickname(nickname).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST)));
    }

    @Override
    @Transactional
    public MemberInfoResDto singIn(MemberSignInReqDto signinInfo) throws IOException, InterruptedException {
        Optional<Member> member = memberRepository.findByEmail(signinInfo.getEmail());

        if (member.isEmpty()) {
            throw new BadRequestException(ErrorCode.USER_NOT_EXIST);
        }

        if (passwordEncoder.matches(signinInfo.getPassword(), member.get().getPassword())) {
            return new MemberInfoResDto(member.get());
        } else {
            throw new BadRequestException(ErrorCode.USER_NOT_MATCH);
        }
    }

    @Override
    public void unRegist(Map<String, Object> memberInfo) throws IOException {
/*
        Object emailObj = memberInfo.get("email");
        Object passwordObj = memberInfo.get("password");

        if (emailObj == null || passwordObj == null) {
            throw new BadRequestException(ErrorCode.INVALID_INPUT);
        }

        String email = emailObj.toString();
        String password = passwordObj.toString();
*/

        //먼저 map값이 null인지 확인
        if (memberInfo.get("email") == null || memberInfo.get("password") == null)
            throw new BadRequestException(ErrorCode.INVALID_INPUT);

        Member member = memberRepository.findByEmail(memberInfo.get("email").toString()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));

        if (passwordEncoder.matches(memberInfo.get("password").toString(), member.getPassword())) {
            memberRepository.delete(member);
        } else {
            throw new BadRequestException(ErrorCode.USER_NOT_MATCH);
        }
    }

    @Override
    public boolean checkEmail(String email) throws IOException {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkNickname(String nickname) throws IOException {
        Optional<Member> member = memberRepository.findByNickname(nickname);
        if (member.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


}
