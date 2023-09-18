package com.ssafy.nashda.question.entity;
import com.ssafy.nashda.user.entity.Member;

import com.ssafy.nashda.common.Entity.TimeEntity;
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

    //    @JsonIgnore
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "member_number", nullable = false)
//    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_index")
    private Reply reply;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

}
