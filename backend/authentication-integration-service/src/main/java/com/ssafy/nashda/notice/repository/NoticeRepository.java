package com.ssafy.nashda.notice.repository;

import com.ssafy.nashda.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notice c SET c.view = :view WHERE c.index = :index")
    void updateView(Long view, Long index);
}