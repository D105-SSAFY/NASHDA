package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.notice.dto.response.NoticeDetailResDto;
import com.ssafy.nashda.notice.dto.request.NoticeReqDto;
import com.ssafy.nashda.notice.entity.Notice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeService {

    // 게시글 생성
    void createNotice (Member member, NoticeReqDto noticeReqDto, List<MultipartFile> files);
    // 게시글 수정
    void updateNotice (Member member, Long index, NoticeReqDto noticeReqDto);

    // 게시글 삭제
    void deleteNotice(Member member, Long noticeIndex);

    // 게시글 전체 조회
    List<Notice> getNotices();

    // 게시글 상세 조회 및 조회수 업데이트
    NoticeDetailResDto getNotice(Long noticeIndex);

}
