package com.ssafy.nashda.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResetPasswordReqDto {
    private String email;
    private String code;
    private String newpassword;
}
