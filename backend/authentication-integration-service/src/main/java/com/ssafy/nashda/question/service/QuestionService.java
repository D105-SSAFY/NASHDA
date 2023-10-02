package com.ssafy.nashda.question.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.request.QuestionReqDto;
import com.ssafy.nashda.question.dto.response.QuestionFileResDto;
import com.ssafy.nashda.question.dto.response.QuestionResDto;
import com.ssafy.nashda.question.entity.Question;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {
    void createQuestion (Member member, QuestionReqDto questionReqDto, List<MultipartFile> files);

    void updateQuestion (Member member, Long index, QuestionReqDto questionReqDto, List<MultipartFile> files);

    void deleteQuestion (Member member, Long QuestionIndex);

    List<QuestionResDto> getQuestions(Member member);

    QuestionResDto getQuestion(Long questionIndex);
}
