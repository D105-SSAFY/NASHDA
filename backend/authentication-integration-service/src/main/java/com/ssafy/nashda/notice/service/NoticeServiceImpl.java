package com.ssafy.nashda.notice.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.notice.dto.response.NoticeDetailResDto;
import com.ssafy.nashda.notice.dto.request.NoticeReqDto;
import com.ssafy.nashda.notice.dto.response.NoticeFileResDto;
import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.notice.entity.NoticeFile;
import com.ssafy.nashda.notice.repository.NoticeFileRepository;
import com.ssafy.nashda.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Transactional
@Slf4j
@Service// 빈으로 등록
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public void createNotice(Member member, NoticeReqDto noticeReqDto, List<MultipartFile> files) {
        if (member.getStatus() == 0) {
            Notice notice = noticeRepository.save(noticeReqDto.toEntity(member));

            if (noticeReqDto.getTitle() == null || noticeReqDto.getTitle().trim().isEmpty()) {
                throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
            }

            if (noticeReqDto.getContent() == null || noticeReqDto.getContent().trim().isEmpty()) {
                throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
            }

            if (files != null) {
                for(MultipartFile file : files) {
                    String uploadUrl;
                    try {
                        if (file.getOriginalFilename().endsWith(".txt")) {
                            continue;
                        } else {
                            uploadUrl = s3Uploader.uploadFiles(file, "notice-files");
                        }
                    } catch (IOException e) {
                        throw new BadRequestException(ErrorCode.FAIL_UPLOAD_FILE);
                    }

                    NoticeFile noticeFile = NoticeFile.builder()
                            .notice(notice)
                            .url(uploadUrl)
                            .fileName(file.getOriginalFilename())
                            .build();

                    noticeFileRepository.save(noticeFile);
                }
            }

        } else {
            throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);
        }
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

        List<NoticeFileResDto> files = new ArrayList<>();
        if (newNotice.getFiles() != null) {
            files = newNotice.getFiles().stream()
                    .map(NoticeFileResDto::new)
                    .collect(Collectors.toList());
        }

        return new NoticeDetailResDto(newNotice, files);
    }

    @Transactional
    public void updateNotice(Member member, Long index, NoticeReqDto noticeReqDto, List<MultipartFile> files) {
        Notice notice = noticeRepository.findById(index).orElseThrow(() -> {return new BadRequestException(ErrorCode.NOT_EXISTS_NOTICE_ID);});

        if (member.getStatus() == 0) {
            if (noticeReqDto != null) {
                String title = noticeReqDto.getTitle();
                String content = noticeReqDto.getContent();

                if (title == null || title.trim().isEmpty()) {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
                } else {
                    notice.setTitle(title);
                }

                if (content == null || content.trim().isEmpty()) {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
                } else {
                    notice.setContent(content);
                }
                // 기존 공지사항에 연결된 파일 목록 조회
                List<NoticeFile> oldFiles = new ArrayList<>(notice.getFiles());
                // 새로운 파일 목록 생성
                List<String> newFileNames = new ArrayList<>();

                if (files != null) {

                    for(MultipartFile file : files) {
                        String fileName = file.getOriginalFilename();
                        newFileNames.add(file.getOriginalFilename());
                        // 해당 파일이 기존 목록에 존재하면 pass
                        if (oldFiles.stream().anyMatch(of -> of.getFileName().equals(fileName))) continue;
                        // 해당 파일이 기존 목록에 존재하지 않으면 s3 업로드
                        String uploadUrl;
                        try {
                            if (file.getOriginalFilename().endsWith(".txt")) {
                                continue;
                            } else {
                                uploadUrl = s3Uploader.uploadFiles(file, "notice-files");
                            }
                        } catch (IOException e) {
                            throw new BadRequestException(ErrorCode.FAIL_UPLOAD_FILE);
                        }

                        NoticeFile noticeFile = NoticeFile.builder()
                                .notice(notice)
                                .url(uploadUrl)
                                .fileName(file.getOriginalFilename())
                                .build();

                        noticeFileRepository.save(noticeFile);
                    }

                }

                Iterator<NoticeFile> iterator = notice.getFiles().iterator();
                while (iterator.hasNext()) {
                    NoticeFile oldFile = iterator.next();
                    if (!newFileNames.contains(oldFile.getFileName())) {
                        iterator.remove();
                        noticeFileRepository.delete(oldFile);
                    }
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
