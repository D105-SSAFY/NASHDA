package com.ssafy.nashda.member.service;

import com.ssafy.nashda.exception.CustomException;
import com.ssafy.nashda.exception.ExceptionEnum;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("MemberService")
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long signUp(MemberSignUpReqDto signUpReqDto) {

//        //비밀번호 encoding해서 member 객체에 저장
//        Member member = new Member(
//                signUpReqDto.getEmail(), signUpReqDto.getName(), signUpReqDto.getNickname(),
//                passwordEncoder.encode(signUpReqDto.getPassword()), signUpReqDto.getAge(), signUpReqDto.getHobbyIdx(), signUpReqDto.getJobIdx()
//        );

        //데이터베이스에서 해당 사용자와 동일한 email을 가진 유저가 있는지 검색한다.
        Optional<Member> exitMember = memberRepository.findByEmail(signUpReqDto.getEmail());

        //비어있다면? 중복된 회원이 없다는 것 -> 회원가입 가능
        if (exitMember.isEmpty()) {
          return memberRepository.save(signUpReqDto.toEntity()).getMemberNum();
        } else {
            System.out.println("실패");
            return null
                    ;
        }

    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new CustomException(ExceptionEnum.USER_NOT_EXIST));
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
}
