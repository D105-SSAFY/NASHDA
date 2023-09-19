package com.ssafy.nashda.question.service;

import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.QuestionResDto;
import com.ssafy.nashda.question.entity.Question;

import java.util.List;

public interface QuestionService {
    Long createQuestion (QuestionReqDto questionReqDto);

    void updateQuestion (Long index, QuestionReqDto questionReqDto);

    void deleteQuestion (Long QuestionIndex);

    List<Question> getQuestions();

    QuestionResDto getQuestion(Long questionIndex);
}
