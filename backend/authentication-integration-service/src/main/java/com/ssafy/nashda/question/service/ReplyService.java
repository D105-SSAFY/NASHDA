package com.ssafy.nashda.question.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.ReplyReqDto;


public interface ReplyService {
    void createReply (Member member, Long boardIndex, ReplyReqDto replyReqDto);

    void updateReply (Member member, Long index, ReplyReqDto replyReqDto);

    void deleteReply (Member member, Long index);
}
