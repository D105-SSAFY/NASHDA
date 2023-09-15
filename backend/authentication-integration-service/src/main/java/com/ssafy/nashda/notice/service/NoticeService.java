package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.dto.NoticeResDto;
import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.notice.repository.NoticeRepository;
import com.ssafy.nashda.user.entity.Member;

import java.util.List;

public interface NoticeService {

    // 게시글 생성
//    Long insert (NoticeReqDto noticeReqDto, Member member);
    Long insert (NoticeReqDto noticeReqDto);
    // 게시글 수정
//    void update (NoticeReqDto notice, Member member);

    // 게시글 삭제
//    void delete(Long noticeIndex, Member member);

    // 게시글 전체 조회
//    List<NoticeResDto> findAll();

    // 게시글 상세 조회
//    NoticeReqDto findById(Long noticeIndex);

}
