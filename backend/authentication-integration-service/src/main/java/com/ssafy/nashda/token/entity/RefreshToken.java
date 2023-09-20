/*
package com.ssafy.nashda.token.entity;

//refreshtoken이랑 useremail을 매핑해놓은 테이블

import com.ssafy.nashda.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_number")
    private Member member;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public RefreshToken(Member member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }

    public RefreshToken(Member member) {
        this.member = member;

    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}
*/
