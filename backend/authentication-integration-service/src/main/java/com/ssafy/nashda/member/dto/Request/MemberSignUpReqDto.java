package com.ssafy.nashda.member.dto.Request;

import com.ssafy.nashda.member.entity.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignUpReqDto {
    //회원가입 시 받을 dto = 정 보
    String name;
    String nickname;
    String password;
    String email;
    int jobIdx;
    int hobbyIdx;
    int age;


    public Member toEntity() {
        return Member.builder().email(email)
                .name(name)
                .nickname(nickname)
                .password(password)
                .age(age)
                .hobbyIdx(hobbyIdx)
                .jobIdx(jobIdx).build();
    }
}
