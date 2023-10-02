package com.ssafy.nashda.question.dto.response;

import com.ssafy.nashda.question.entity.Question;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResDto {
    private Long index;
    private String title;
    private String content;
    private LocalDateTime updateOn;
    private ReplyResDto reply;


    public QuestionResDto (Question question, ReplyResDto replyResDto) {
        this.index = question.getIndex();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.updateOn = question.getUpdateOn();
        this.reply = replyResDto;
    }
}
