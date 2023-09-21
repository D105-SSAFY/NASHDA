package com.ssafy.nashda.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends TimeEntity {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "notice_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
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
