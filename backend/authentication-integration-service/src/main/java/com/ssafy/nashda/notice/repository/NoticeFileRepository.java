package com.ssafy.nashda.notice.repository;

import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.notice.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {

    List<NoticeFile> findNoticeFileByNotice(Notice notice);
}
