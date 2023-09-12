package com.nashda.board.notice.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
public class NoticeDto {
    private long notice_id;
    private long member_id;

}
