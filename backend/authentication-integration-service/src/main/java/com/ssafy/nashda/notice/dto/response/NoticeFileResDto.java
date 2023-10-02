package com.ssafy.nashda.notice.dto.response;

import com.ssafy.nashda.notice.entity.NoticeFile;
import lombok.*;

@Getter
@Setter
public class NoticeFileResDto {

    private String fileName;
    private String url;

    public NoticeFileResDto (NoticeFile noticeFile) {
        this.fileName = noticeFile.getFileName();
        this.url = noticeFile.getUrl();
    }
}
