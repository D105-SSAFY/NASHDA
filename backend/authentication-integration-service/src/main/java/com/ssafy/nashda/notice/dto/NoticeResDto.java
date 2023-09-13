package com.ssafy.nashda.notice.dto;

import com.ssafy.nashda.notice.entity.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResDto {
    private Long index;
    private String title;
    private String content;
    private Long view; // 조회수
    private LocalDateTime createOn;
    private LocalDateTime updateOn;

    public NoticeResDto(Notice entity) {
        this.index = entity.getIndex();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.view = entity.getView();
        this.createOn = entity.getCreateOn();
        this.updateOn = entity.getUpdateOn();
    }
}
