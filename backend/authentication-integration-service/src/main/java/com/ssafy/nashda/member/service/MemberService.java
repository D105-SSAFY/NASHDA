package com.ssafy.nashda.member.service;

import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {

    //회원가입
    Long signUp(MemberSignUpReqDto signUpReqDto) throws IOException;
    Member findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
