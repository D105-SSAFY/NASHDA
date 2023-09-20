package com.ssafy.nashda.question.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.QuestionResDto;
import com.ssafy.nashda.question.dto.ReplyResDto;
import com.ssafy.nashda.question.entity.Question;
import com.ssafy.nashda.question.entity.Reply;
import com.ssafy.nashda.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public void createQuestion(Member member, QuestionReqDto questionReqDto) {
        questionRepository.save(questionReqDto.toEntity(member));
    }

    @Override
    @Transactional
    public List<Question> getQuestions(Member member) {
        return questionRepository.findQuestionsByMember(member.getMemberNum());
    }

    @Override
    @Transactional
    public QuestionResDto getQuestion(Long index) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {
            return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);
        });

        Reply reply = question.getReply();
        ReplyResDto replyResDto = (reply != null) ? new ReplyResDto(reply) : null;

        QuestionResDto questionResDto = new QuestionResDto(question, replyResDto);
        questionResDto.setReply(replyResDto);

        return questionResDto;
    }


    @Transactional
    public void updateQuestion(Member member, Long index, QuestionReqDto questionReqDto) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);});

        if (member.getMemberNum().equals(question.getMember().getMemberNum())) {
            if (questionReqDto != null) {
                String title = questionReqDto.getTitle();
                String content = questionReqDto.getContent();

                if (title != null) {
                    question.setTitle(title);
                } else {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
                }

                if (content != null) {
                    question.setContent(content);
                } else {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
                }
                return;
            }
        }
        throw new BadRequestException(ErrorCode.NOT_EQUAL_USER);
    }

    @Transactional
    public void deleteQuestion(Member member, Long index) {
        Question question = questionRepository.findById(index).orElseThrow(()-> {return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);});

        if (member.getMemberNum().equals(question.getMember().getMemberNum())) {
            questionRepository.deleteById(index);
            return;
        }
        throw new BadRequestException(ErrorCode.NOT_EQUAL_USER);
    }
}
