package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.dto.NoticeResDto;
import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.notice.repository.NoticeRepository;
import com.ssafy.nashda.user.entity.Member;
import io.lettuce.core.GeoArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;

    // 게시글 생성
    @Transactional
    public Long saveNotice(Member member, NoticeReqDto noticeReqDto) {
        Notice entity = noticeRepository.save(noticeReqDto.toEntity(member));
        return entity.getIndex();
    }

    // 게시글 전체 목록 조회
    @Transactional
    public List<NoticeResDto> findAll() {
        Sort sort = Sort.by(Direction.DESC, "index", "createOn");
        List<Notice> list = noticeRepository.findAll(sort);
        return list.stream().map(NoticeResDto::new).collect(Collectors.toList());
    }

    // 게시글 수정
//    @Transactional
//    public Long update(final Long index, final NoticeReqDto params) {
//        Notice entity = noticeRepository.findById(index).orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
//        entity.update(params.getTitle(), params.getContent());
//        return id;
//    }
}
