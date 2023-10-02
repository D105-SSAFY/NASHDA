package com.ssafy.nashda.question.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;

import com.ssafy.nashda.common.entity.TimeEntity;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "question_index")
    private Long index;

    @OneToOne(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Reply reply;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionFile> files;

    public void setReply(Reply reply) {
        this.reply = reply;
        if (reply != null) {
            reply.setQuestion(this);
        }
    }

    @Builder
    public Question (String title, String content, Member member, List<QuestionFile> files) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.files = files;
    }
}
