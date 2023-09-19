package com.ssafy.nashda.question.dto;

import com.ssafy.nashda.question.entity.Question;
import lombok.*;

@Getter
@NoArgsConstructor
public class QuestionReqDto {
    private String title;
    private String content;

    @Builder
    public QuestionReqDto(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}
