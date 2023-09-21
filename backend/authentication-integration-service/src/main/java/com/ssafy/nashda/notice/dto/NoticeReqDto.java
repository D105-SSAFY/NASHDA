package com.ssafy.nashda.notice.dto;

import com.ssafy.nashda.notice.entity.Notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeReqDto {
    private String title;
    private String content;

    @Builder
    public NoticeReqDto(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }

//    public Notice toEntity(Member member) {
//        return Notice.builder()
//                .member(member)
//                .title(title)
//                .content(content)
//                .build();
//    }

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .build();
    }
}
