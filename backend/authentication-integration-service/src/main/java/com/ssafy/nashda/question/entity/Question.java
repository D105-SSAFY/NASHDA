package com.ssafy.nashda.question.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;

import com.ssafy.nashda.common.entity.TimeEntity;
import jdk.jfr.Category;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long index;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name= "reply_index")
    private Reply reply;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    public void setReply(Reply reply) {
        this.reply = reply;
        if (reply != null) {
            reply.setQuestion(this);
        }
    }

    @Builder
    public Question (String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
