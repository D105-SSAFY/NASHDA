package com.ssafy.nashda.notice.dto.response;

import com.ssafy.nashda.notice.entity.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class NoticeAllResDto {

    private Long index;
    private String title;
    private Long view; // 조회수
    private LocalDateTime updateOn;

    public NoticeAllResDto(Notice notice) {
        this.index = notice.getIndex();
        this.title = notice.getTitle();
        this.view = notice.getView();
        this.updateOn = notice.getUpdateOn();
    }

}
