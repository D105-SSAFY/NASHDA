package com.ssafy.nashda.statistic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Strick {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "strick_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    @Column(name = "create_on")
    LocalDate creatOn;

    @Column(name = "blank_count")
    private int blankCount;
    @Column(name = "speed_count")
    private int speedCount;
    @Column(name = "practice_count")
    private int practiceCount;
    @Column(name ="conversation_count")
    private int conversationCount;
    @Column(name = "test_count")
    private int testCount;

    @PrePersist
    public void prePersist() {
        this.creatOn = LocalDate.now();
    }

    @Builder
    public Strick(Member member) {
        this.member = member;
    }

}
