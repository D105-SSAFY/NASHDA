package com.ssafy.nashda.member.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service("MemberService")
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long signUp(MemberSignUpReqDto signUpReqDto) {

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

            return memberRepository.save(member).getMemberNum();
        } else {
            return (long) -1;
        }


    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Override
    @Transactional
    public MemberInfoResDto singIn(MemberSignInReqDto signinInfo) throws IOException, InterruptedException {

        Member member = memberRepository.findByEmail(signinInfo.getEmail()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));

        if (passwordEncoder.matches(signinInfo.getPassword(), member.getPassword())) {
            return new MemberInfoResDto(member);
        } else {
            throw new BadRequestException(ErrorCode.USER_NOT_MATCH);

        }

    }
}
