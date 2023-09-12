package com.nashda.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
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

    @Column(name = "create_on")
    private Date createOn;

    @Column(name = "update_on")
    private Date updateOn;


    @Builder
    public Member(String email, String name, String nickname, String password, int age, int hobbyIdx, int jobIdx, int status, int progress, int wordCount, int sentenceCount, int conversationCount) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.age = age;
        this.hobbyIdx = hobbyIdx;
        this.jobIdx = jobIdx;
        this.status = status;
        this.progress = progress;
        this.wordCount = wordCount;
        this.sentenceCount = sentenceCount;
        this.conversationCount = conversationCount;
    }


}
