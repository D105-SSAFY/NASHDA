package com.ssafy.nashda.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.Entity.TimeEntity;
import com.ssafy.nashda.user.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Entity // 객체와 테이블 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice_board") // 클래스명과 데이터베이스 테이블명이 다르면 지정해줘야함.
public class Notice extends TimeEntity {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Long view;


    @Builder
    public Notice(Long index, String title, String content, Long view, Member member) {
        this.title = title;
        this.content = content;
        this.view = 0L;
        this.member = member;
    }

    public void update(String title, String content, Member member) {
        this.title = title;
        this.content = content;
    }
}
