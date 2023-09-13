package com.ssafy.nashda.notice.dto;

import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.user.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeReqDto {
    private Long member;
    private String title;
    private String content;

    @Builder
    public NoticeReqDto(Member member, String title, String content) {
        this.member = member.getMemberNum();
        this.title = title;
        this.content = content;
    }

    public Notice toEntity(Member member, Notice notice) {
        return Notice.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
