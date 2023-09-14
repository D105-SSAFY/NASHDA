package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.repository.NoticeRepository;
import com.ssafy.nashda.user.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Transactional
@Slf4j
@Service// 빈으로 등록
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    // 게시글 생성
    @Override
    public Long insert(NoticeReqDto noticeReqDto, Member member) {
        return noticeRepository.save(noticeReqDto.toEntity(member)).getIndex();
    }
}
