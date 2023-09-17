package com.ssafy.nashda.member.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignInReqDto {
    String email;
    String password;
}