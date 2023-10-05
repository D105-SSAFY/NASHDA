package com.ssafy.nashda.question.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.ssafy.nashda.question.entity.Reply;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) // 모든 가시성 필드를 직렬화
public class ReplyResDto {
    private String title;
    private String content;
    private LocalDateTime updateOn;

    public ReplyResDto(Reply reply) {
        this.title = reply.getTitle();
        this.content = reply.getContent();
        this.updateOn = reply.getUpdateOn();
    }

}
