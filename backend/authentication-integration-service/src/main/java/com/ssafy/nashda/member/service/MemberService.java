package com.ssafy.nashda.member.service;

import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {
    void signUp(MemberSignUpReqDto signUpReqDto) throws IOException;
    Member findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    MemberInfoResDto singIn(MemberSignInReqDto signinInfo) throws IOException, InterruptedException;
    void unRegist(MemberSignInReqDto signInReqDto) throws IOException;
}
