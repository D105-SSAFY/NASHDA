package com.ssafy.nashda.board.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice_board") // 클래스명과 데이터베이스 테이블명이 다르면 지정해줘야함.
public class Notice {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    private Long index;

//    @ManyToOne
//    @JoinColumn(name = "member_number")
//    private Member memberNum;

    private String title;
    private String content;
    private Long view;

    @Builder
    public Notice(String title, String content, Long view) {
        this.title = title;
        this.content = content;
        this.view = 0L;
//        this.member = member;
    }
}
