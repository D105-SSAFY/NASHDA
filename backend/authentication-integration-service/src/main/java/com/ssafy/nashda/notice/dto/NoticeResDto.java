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

    public NoticeResDto(Notice notice) {
        this.index = notice.getIndex();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.view = notice.getView();
        this.createOn = notice.getCreateOn();
        this.updateOn = notice.getUpdateOn();
    }

//    public NoticeAllResDto(Notice notice) {
//        this.index = notice.getIndex();
//    }
}
