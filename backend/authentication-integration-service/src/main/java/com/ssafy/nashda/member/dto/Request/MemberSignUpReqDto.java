package com.ssafy.nashda.member.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpReqDto {
    //회원가입 시 받을 dto = 정보
    String name;
    String nickname;
    String password;
    String email;
    int jobIdx;
    int hobbyIdx;
    int age;
}
