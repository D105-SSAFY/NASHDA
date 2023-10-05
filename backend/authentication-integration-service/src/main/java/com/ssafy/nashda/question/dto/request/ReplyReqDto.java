package com.ssafy.nashda.question.dto.request;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.entity.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyReqDto {
    private String title;
    private String content;

    @Builder
    public ReplyReqDto(Reply reply) {
        this.title = reply.getTitle();
        this.content = reply.getContent();
    }

    public Reply toEntity(Member member) {
        return Reply.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
