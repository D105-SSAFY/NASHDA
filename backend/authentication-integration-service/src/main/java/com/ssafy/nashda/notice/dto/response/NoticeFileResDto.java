package com.ssafy.nashda.notice.dto.response;

import com.ssafy.nashda.notice.entity.NoticeFile;
import lombok.*;

@Getter
@Setter
public class NoticeFileResDto {

    private String url;
    private String fileName;

    public NoticeFileResDto of(NoticeFile noticeFile) {
        NoticeFileResDto noticeFileResDto = new NoticeFileResDto();
        noticeFileResDto.setUrl(noticeFile.getUrl());
        noticeFileResDto.setFileName(noticeFile.getFileName());
        return noticeFileResDto;
    }
}
