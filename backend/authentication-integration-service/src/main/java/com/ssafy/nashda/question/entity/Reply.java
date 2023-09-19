package com.ssafy.nashda.question.entity;

import com.ssafy.nashda.common.entity.TimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends TimeEntity {
    @Id
    private Long index;

    @OneToOne
    @MapsId
    @JoinColumn(name = "question_index")
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
