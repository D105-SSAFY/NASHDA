package com.ssafy.nashda.game.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlankSetSaveReqDto {
    private MultipartFile img;
    private String correctAnswer;

    private String word1;
    private String word1Type;
    private String word1Description;

    private String word2;
    private String word2Type;
    private String word2Description;

    private String word3;
    private String word3Type;
    private String word3Description;
}
