package com.ssafy.nashda.notice.repository;

import com.ssafy.nashda.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // 공지사항 상세 조회시 조회수 증가
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notice c SET c.view = :view WHERE c.index = :index")
    void updateView(Long view, Long index);
}