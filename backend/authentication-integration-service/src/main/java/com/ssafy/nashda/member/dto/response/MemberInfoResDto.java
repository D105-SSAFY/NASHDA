package com.ssafy.nashda.member.dto.response;

import com.ssafy.nashda.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MemberInfoResDto {
    private String nickname;
    private String name;
    private String email;
    private int age;
    private int job;
    private int hobby;
    private int progress;
    private LocalDateTime createOn;

    public MemberInfoResDto(Member member) {
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.job = member.getJobIdx();
        this.hobby = member.getHobbyIdx();
        this.progress = member.getProgress();
        this.createOn = member.getCreateOn();
    }
}
