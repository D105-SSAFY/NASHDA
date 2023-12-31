package com.ssafy.nashda.question.dto.request;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.entity.Question;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class QuestionReqDto {
    private String title;
    private String content;

    @Builder
    public QuestionReqDto(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
    }

    public Question toEntity(Member member) {
        return Question.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}
