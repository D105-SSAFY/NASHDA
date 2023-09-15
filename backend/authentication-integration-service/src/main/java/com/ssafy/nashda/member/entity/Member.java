package com.ssafy.nashda.member.entity;

import com.ssafy.nashda.common.Entity.TimeEntity;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table
public class Member extends TimeEntity {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincreasement 설정

    @Column(name = "member_number")
    private Long memberNum;

    @NotNull
    private String email;


    @NotNull
    private String name;
    @NotNull
    private String nickname;
    @NotNull
    private String password;


    private int age;

    @Column(name = "hobby_index")
    private int hobbyIdx;

    @Column(name = "job_index")
    private int jobIdx;

    @ColumnDefault("1")
    private int status;

    @ColumnDefault("0")
    private int progress;

    @ColumnDefault("0")
    @Column(name = "word_count")
    private int wordCount;

    @ColumnDefault("0")
    @Column(name = "sentence_count")
    private int sentenceCount;

    @Column(name = "conversation_count")
    @ColumnDefault("0")
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
