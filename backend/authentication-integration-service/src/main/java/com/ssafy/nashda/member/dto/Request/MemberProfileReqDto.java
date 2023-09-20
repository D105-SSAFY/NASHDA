package com.ssafy.nashda.member.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileReqDto {
    String name;
    String nickname;
    int age;
    int job;
    int hobby;

    public MemberProfileReqDto(String name, String nickanme){
        this.name = name;
        this.nickname = nickanme;
    }..


    
}
