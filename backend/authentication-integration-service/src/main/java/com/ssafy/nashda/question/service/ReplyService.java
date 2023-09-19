package com.ssafy.nashda.question.service;

import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.QuestionResDto;
import com.ssafy.nashda.question.dto.ReplyReqDto;
import com.ssafy.nashda.question.dto.ReplyResDto;
import com.ssafy.nashda.question.entity.Question;
import com.ssafy.nashda.question.entity.Reply;

import java.util.List;

public interface ReplyService {
    Long createReply (Long boardIndex, ReplyReqDto replyReqDto);

    void updateReply (Long index, ReplyReqDto replyReqDto);

    void deleteReply (Long index);
}
