package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
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
    public void createNotice(Member member, NoticeReqDto noticeReqDto) {
        if (member.getStatus() == 0) {
            noticeRepository.save(noticeReqDto.toEntity(member));
            return;
        }
        throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);
    }

    @Override
    @Transactional
    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    @Override
    @Transactional
    public NoticeDetailResDto getNotice(Long index) {
        Notice notice = noticeRepository.findById(index).orElseThrow(() -> {return new BadRequestException(ErrorCode.NOT_EXISTS_NOTICE_ID);});
        Long view = notice.getView();
        noticeRepository.updateView(view + 1, index);
        Notice newNotice = noticeRepository.getReferenceById(index);
        return new NoticeDetailResDto(newNotice);
    }

    @Transactional
    public void updateNotice(Member member, Long index, NoticeReqDto noticeReqDto) {
        Notice notice = noticeRepository.findById(index).orElseThrow(() -> {return new BadRequestException(ErrorCode.NOT_EXISTS_NOTICE_ID);});

        if (member.getStatus() == 0) {
            if (noticeReqDto != null) {
                String title = noticeReqDto.getTitle();
                String content = noticeReqDto.getContent();

                if (title != null) {
                    notice.setTitle(title);
                } else {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
                }


                if (content != null) {
                    notice.setContent(content);
                } else {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
                }
                return;
            }
        }

        throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);
    }

    @Transactional
    public void deleteNotice(Member member, Long index) {
        noticeRepository.findById(index).orElseThrow(() -> {return new BadRequestException(ErrorCode.NOT_EXISTS_NOTICE_ID);});

        if (member.getStatus() == 0) {
            noticeRepository.deleteById(index);
            return;
        }

        throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);
    }

}
