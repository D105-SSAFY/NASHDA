package com.ssafy.nashda.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeFile> files;


    @Builder
    public Notice(Long index, String title, String content, Long view, Member member, List<NoticeFile> files) {
        this.title = title;
        this.content = content;
        this.view = 0L;
        this.member = member;
        this.files = files;
    }

    public void update(String title, String content, Member member) {
        this.title = title;
        this.content = content;
    }
}
