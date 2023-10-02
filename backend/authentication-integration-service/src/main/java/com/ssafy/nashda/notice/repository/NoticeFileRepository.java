package com.ssafy.nashda.notice.repository;

import com.ssafy.nashda.notice.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {
}
