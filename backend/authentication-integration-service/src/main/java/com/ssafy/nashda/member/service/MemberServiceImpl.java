package com.ssafy.nashda.member.service;

import com.ssafy.nashda.exception.CustomException;
import com.ssafy.nashda.exception.ExceptionEnum;
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


    @Override
    @Transactional
    public void signUp(MemberSignUpReqDto signUpReqDto) throws IOException {
        Member member = new Member(
                signUpReqDto.getEmail(), signUpReqDto.getName(), signUpReqDto.getNickname(),
                signUpReqDto.getPassword(), signUpReqDto.getAge(), signUpReqDto.getHobbyIdx(), signUpReqDto.getJobIdx()
        );

        //데이터베이스에서 해당 사용자와 동일한 email을 가진 유저가 있는지 검색한다.
        Optional<Member> exitMember = memberRepository.findByEmail(member.getEmail());
        
        //비어있다면? 중복된 회원이 없다는 것 -> 회원가입 가능
        if(exitMember.isEmpty()){
            memberRepository.save(member);
        }else{
            throw new CustomException(ExceptionEnum.USER_EXIST);
        }

    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionEnum.USER_NOT_EXIST));
    }
}
