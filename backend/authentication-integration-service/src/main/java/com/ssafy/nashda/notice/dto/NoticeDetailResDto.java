package com.ssafy.nashda.notice.dto;

import com.ssafy.nashda.notice.entity.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeDetailResDto {
    private Long index;
    private String title;
    private String content;
    private Long view; // 조회수
    private LocalDateTime updateOn;

    public NoticeDetailResDto(Notice notice) {
        this.index = notice.getIndex();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.view = notice.getView();
        this.updateOn = notice.getUpdateOn();
    }
}
