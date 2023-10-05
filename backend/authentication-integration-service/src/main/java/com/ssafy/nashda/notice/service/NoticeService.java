package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.notice.dto.response.NoticeDetailResDto;
import com.ssafy.nashda.notice.dto.request.NoticeReqDto;
import com.ssafy.nashda.notice.entity.Notice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeService {

    void createNotice (Member member, NoticeReqDto noticeReqDto, List<MultipartFile> files);
    void updateNotice (Member member, Long index, NoticeReqDto noticeReqDto, List<MultipartFile> files);

    void deleteNotice(Member member, Long noticeIndex);

    List<Notice> getNotices();

    NoticeDetailResDto getNotice(Long noticeIndex);

}
