package com.ssafy.nashda.member.entity;

import com.ssafy.nashda.common.Entity.TimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")  // 테이블 지정
@EntityListeners(AuditingEntityListener.class)
public class Member extends TimeEntity {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincreasement 설정

    @Column(name = "member_number")
    private Long memberNum;

    private String email;
    private String name;
    private String nickname;
    private String password;

    private int age;

    @Column(name = "hobby_index")
    private int hobbyIdx;

    @Column(name = "job_index")
    private int jobIdx;

    private int status;
    private int progress;

    @Column(name = "word_count")
    private int wordCount;

    @Column(name = "sentence_count")
    private int sentenceCount;

    @Column(name = "conversation_count")
    private int conversationCount;


    @Builder
    public Member(String email, String name, String nickname, String password, int age, int hobbyIdx, int jobIdx) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.age = age;
        this.hobbyIdx = hobbyIdx;
        this.jobIdx = jobIdx;
    }
}
