package com.ssafy.nashda.question.entity;

import com.ssafy.nashda.common.Entity.TimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long index;

    @OneToOne(mappedBy = "reply", cascade = CascadeType.PERSIST)
    private Question question;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Reply(String title, String content, Question question) {
        this.title = title;
        this.content = content;
        this.question = question;
    }
}
