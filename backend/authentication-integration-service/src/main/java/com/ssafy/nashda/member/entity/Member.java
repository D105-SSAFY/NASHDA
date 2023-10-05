package com.ssafy.nashda.member.entity;

import com.ssafy.nashda.common.entity.TimeEntity;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import com.sun.istack.NotNull;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "members")
public class Member extends TimeEntity {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_number")
    private Long memberNum;

    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String nickname;
    @NotNull
    private String password;

    private int age;

    @Column(name = "hobby_index")
    private int hobbyIdx;

    @Column(name = "job_index")
    private int jobIdx;

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

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<MemberAchievement> memberAchievements = new HashSet<>();


    @Builder
    public Member(String email, String name, String nickname, String password, int age, int hobbyIdx, int jobIdx) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.age = age;
        this.hobbyIdx = hobbyIdx;
        this.jobIdx = jobIdx;
        this.status = 1;
    }


}
