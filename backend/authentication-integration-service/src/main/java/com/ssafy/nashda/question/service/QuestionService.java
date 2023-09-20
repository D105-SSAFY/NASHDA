package com.ssafy.nashda.question.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.QuestionResDto;
import com.ssafy.nashda.question.entity.Question;

import java.util.List;

public interface QuestionService {
    void createQuestion (Member member, QuestionReqDto questionReqDto);

    void updateQuestion (Member member, Long index, QuestionReqDto questionReqDto);

    void deleteQuestion (Member member, Long QuestionIndex);

    List<Question> getQuestions(Member member);

    QuestionResDto getQuestion(Long questionIndex);
}
