package com.ssafy.nashda.question.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.ReplyReqDto;
import com.ssafy.nashda.question.entity.Question;
import com.ssafy.nashda.question.entity.Reply;
import com.ssafy.nashda.question.repository.QuestionRepository;
import com.ssafy.nashda.question.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public void createReply(Member member, Long boardIndex, ReplyReqDto replyReqDto) {
        Question question = questionRepository.findById(boardIndex).orElseThrow(() -> {
            return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);
        });

        if (question.getReply() != null) {
            throw new BadRequestException(ErrorCode.EXIST_REPLY);
        }

        if (member.getStatus() == 0) {
            Reply reply = Reply.builder()
                    .title(replyReqDto.getTitle())
                    .content(replyReqDto.getContent())
                    .question(question)
                    .member(member)
                    .build();

            // 질문에 답변 연결
            question.setReply(reply);
            replyRepository.save(reply);
            return;
        }

        throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);

    }

    @Transactional
    public void updateReply(Member member, Long index, ReplyReqDto replyReqDto) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {
            return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);
        });

        Reply reply = replyRepository.findByQuestion(question).orElseThrow(() -> {
            return new BadRequestException(ErrorCode.NOT_EXISTS_REPLY_ID);
        });

        if (member.getStatus() == 0) {
            if (replyReqDto != null) {
                String title = replyReqDto.getTitle();
                String content = replyReqDto.getContent();

                if (title != null) {
                    reply.setTitle(title);
                } else {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
                }

                if (content != null) {
                    reply.setContent(content);
                } else {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
                }
                return;

            }
        }
        throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);
    }

    @Transactional
    public void deleteReply(Member member, Long index) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {
            return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);
        });

        Reply reply = replyRepository.findByQuestion(question).orElseThrow(() -> {
            return new BadRequestException(ErrorCode.NOT_EXISTS_REPLY_ID);
        });

        if (member.getStatus() == 0) {
            replyRepository.delete(reply);
            return;
        }
        throw new BadRequestException(ErrorCode.NOT_VALID_AUTHORIZATION);
    }
}