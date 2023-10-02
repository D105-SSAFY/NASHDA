package com.ssafy.nashda.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class NoticeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "noticefile_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_index", nullable = false)
    private Notice notice;

    @Column(length = 1000)
    private String url;

    private String fileName;

    @Builder
    public NoticeFile(Notice notice, String url, String fileName) {
        this.notice = notice;
        this.url = url;
        this.fileName = fileName;
    }
}