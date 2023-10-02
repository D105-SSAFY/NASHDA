package com.ssafy.nashda.notice.dto.request;

import  lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeFileReqDto {
    private MultipartFile file;
}
