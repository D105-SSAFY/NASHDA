package com.ssafy.nashda.question.dto.response;

import com.ssafy.nashda.question.entity.QuestionFile;
import lombok.*;

@Getter
@Setter
public class QuestionFileResDto {

    private String fileName;
    private String url;

    public QuestionFileResDto (QuestionFile questionFile) {
        this.fileName = questionFile.getFileName();
        this.url = questionFile.getUrl();
    }
}
