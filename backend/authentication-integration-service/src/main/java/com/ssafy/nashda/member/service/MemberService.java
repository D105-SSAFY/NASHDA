package com.ssafy.nashda.member.service;

import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface MemberService {
    void signUp(MemberSignUpReqDto signUpReqDto) throws IOException;
    Member findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    MemberInfoResDto singIn(MemberSignInReqDto signinInfo) throws IOException, InterruptedException;
    void unRegist(Map<String, Object> memberInfo) throws IOException;
    boolean checkEmail(String email) throws IOException;
    boolean checkNickname(String nickname) throws IOException;
    void updateProfile(Map<String, Object> profileInfo) throws IOException;
    void updatePassword(Map<String, Object> passwords) throws IOException;
    void resetPassword(Map<String, Object> passwords) throws IOException;
}
