package com.ssafy.nashda.question.dto;

import com.ssafy.nashda.question.entity.Reply;

import java.time.LocalDateTime;

public class ReplyResDto {
    private Long index;
    private String title;
    private String content;
    private LocalDateTime updateOn;
    private Long questionIndex;

    public ReplyResDto(Reply reply) {
        this.index = reply.getIndex();
        this.title = reply.getTitle();
        this.content = reply.getContent();
        this.updateOn = reply.getCreateOn();
    }

}
