package com.ssafy.nashda.question.dto;

import com.ssafy.nashda.question.entity.Reply;

import java.time.LocalDateTime;

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
