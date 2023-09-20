package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.notice.dto.NoticeDetailResDto;
import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Transactional
@Slf4j
@Service// 빈으로 등록
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public Long createNotice(Member member, NoticeReqDto noticeReqDto) {
        return noticeRepository.save(noticeReqDto.toEntity(member)).getIndex();
    }

    @Override
    @Transactional
    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    @Override
    @Transactional
    public NoticeDetailResDto getNotice(Long index) {
        Notice notice = noticeRepository.findById(index).orElseThrow(() -> {return new IllegalArgumentException("존재하지 않는 게시물입니다.");});
        Long view = notice.getView();
        noticeRepository.updateView(view + 1, index);
        Notice newNotice = noticeRepository.getReferenceById(index);
        return new NoticeDetailResDto(newNotice);
    }

    @Transactional
    public void updateNotice(Long index, NoticeReqDto noticeReqDto) {
        Notice notice = noticeRepository.findById(index).orElseThrow(() -> {return new IllegalArgumentException("존재하지 않는 게시물입니다.");});
        if (noticeReqDto != null) {
            String title = noticeReqDto.getTitle();
            String content = noticeReqDto.getContent();

            if (title != null) {
                notice.setTitle(title);
            }

            if (content != null) {
                notice.setContent(content);
            }

        }
    }

    @Transactional
    public void deleteNotice(Long index) {
        Notice notice = noticeRepository.findById(index).orElseThrow(() -> {return new IllegalArgumentException("존재하지 않는 게시물입니다.");});

        noticeRepository.deleteById(index);
    }

}
