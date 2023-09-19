package com.ssafy.nashda.question.service;

import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.ReplyReqDto;
import com.ssafy.nashda.question.dto.ReplyResDto;
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
    public Long createReply(Long boardIndex, ReplyReqDto replyReqDto) {
        Question question = questionRepository.findById(boardIndex).orElseThrow(() -> {
            return new IllegalArgumentException("존재하지 않는 답변입니다.");
        });

        Reply reply = Reply.builder()
                .title(replyReqDto.getTitle())
                .content(replyReqDto.getContent())
                .question(question)
                .build();

        // 질문에 답변 연결
        question.setReply(reply);

        Reply savedReply = replyRepository.save(reply);

        return savedReply.getQuestion().getIndex();
    }

    @Transactional
    public void updateReply(Long index, ReplyReqDto replyReqDto) {
        Reply reply = replyRepository.findById(index).orElseThrow(() -> {
            return new IllegalArgumentException("존재하지 않는 답변입니다.");
        });

        if (replyReqDto != null) {
            String title = replyReqDto.getTitle();
            String content = replyReqDto.getContent();

            if (title != null) {
                reply.setTitle(title);
            }

            if (content != null) {
                reply.setContent(content);
            }
        }

    }

    @Transactional
    public void deleteReply(Long index) {
        Reply reply = replyRepository.findById(index).orElseThrow(() -> {
            return new IllegalArgumentException("존재하지 않는 게시물입니다.");
        });

        replyRepository.deleteById(index);
    }
}