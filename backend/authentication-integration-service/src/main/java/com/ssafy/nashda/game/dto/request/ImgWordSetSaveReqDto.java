package com.ssafy.nashda.game.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImgWordSetSaveReqDto {
    private MultipartFile img;
    private String word;
    private String type;
    private String description;

}
