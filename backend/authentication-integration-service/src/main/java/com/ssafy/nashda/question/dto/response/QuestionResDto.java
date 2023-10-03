package com.ssafy.nashda.question.dto.response;

import com.ssafy.nashda.question.entity.Question;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResDto {
    private Long index;
    private String title;
    private String content;
    private LocalDateTime updateOn;
    private List<QuestionFileResDto> files;
    private ReplyResDto reply;



    public QuestionResDto (Question question, ReplyResDto replyResDto, List<QuestionFileResDto> files) {
        this.index = question.getIndex();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.updateOn = question.getUpdateOn();
        this.files = files;
        this.reply = replyResDto;
    }
}
