package com.ssafy.nashda.notice.entity;

import com.ssafy.nashda.common.entity.TimeEntity;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice_board") // 클래스명과 데이터베이스 테이블명이 다르면 지정해줘야 함.
public class Notice extends TimeEntity {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false)
    private Long index;

//    @JsonIgnore
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "member_number", nullable = false)
//    private Member member;

    private String email;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Long view;


//    @Builder
//    public Notice(Long index, String title, String content, Long view, Member member) {
//        this.title = title;
//        this.content = content;
//        this.view = 0L;
//        this.member = member;
//    }

    @Builder
    public Notice(Long index, String title, String content, Long view, String email) {
        this.title = title;
        this.content = content;
        this.view = 0L;
        this.email = "temp@temp.com";
    }

    public void update(String title, String content, Member member) {
        this.title = title;
        this.content = content;
    }
}
