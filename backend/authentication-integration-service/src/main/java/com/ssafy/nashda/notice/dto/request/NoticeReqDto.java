package com.ssafy.nashda.notice.dto.request;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.notice.entity.Notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeReqDto {
    private String title;
    private String content;

    @Builder
    public NoticeReqDto(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }

    public Notice toEntity(Member member) {
        return Notice.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }

}
