package com.ssafy.nashda.question.service;

import com.ssafy.nashda.notice.dto.NoticeDetailResDto;
import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.QuestionResDto;
import com.ssafy.nashda.question.dto.ReplyResDto;
import com.ssafy.nashda.question.entity.Question;
import com.ssafy.nashda.question.entity.Reply;
import com.ssafy.nashda.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.AsyncWebRequestInterceptor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Long createQuestion(QuestionReqDto questionReqDto) {
        return questionRepository.save(questionReqDto.toEntity()).getIndex();
    }

    // 멤버를 받아서 해당 멤버가 작성한 질문 사항만 보여줘야 함.
    @Override
    @Transactional
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public QuestionResDto getQuestion(Long index) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {
            return new IllegalArgumentException("존재하지 않는 게시물입니다.");
        });

        Reply reply = question.getReply();
        ReplyResDto replyResDto = (reply != null) ? new ReplyResDto(reply) : null;

        QuestionResDto questionResDto = new QuestionResDto(question, replyResDto);
        questionResDto.setReply(replyResDto);

        return questionResDto;
    }


    @Transactional
    public void updateQuestion(Long index, QuestionReqDto questionReqDto) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {return new IllegalArgumentException("존재하지 않는 게시물 입니다.");});

        if (questionReqDto != null) {
            String title = questionReqDto.getTitle();
            String content = questionReqDto.getContent();

            if (title != null) {
                question.setTitle(title);
            }

            if (content != null) {
                question.setContent(content);
            }
        }
    }

    @Transactional
    public void deleteQuestion(Long index) {
        Question question = questionRepository.findById(index).orElseThrow(()-> {return new IllegalArgumentException("존재하지 않는 게시물입니다.");});
        questionRepository.deleteById(index);
    }
}
