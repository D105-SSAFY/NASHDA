package com.ssafy.nashda.notice.dto.response;

import com.ssafy.nashda.notice.entity.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class NoticeDetailResDto {
    private Long index;
    private String title;
    private String content;
    private Long view; // 조회수
    private LocalDateTime updateOn;

    private List<NoticeFileResDto> files;

    public NoticeDetailResDto(Notice notice, List<NoticeFileResDto> files) {
        this.index = notice.getIndex();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.view = notice.getView();
        this.updateOn = notice.getUpdateOn();
        this.files = files;
    }
}
