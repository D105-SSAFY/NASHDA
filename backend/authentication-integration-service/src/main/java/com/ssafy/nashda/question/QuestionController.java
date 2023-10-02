package com.ssafy.nashda.question;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.request.QuestionReqDto;
import com.ssafy.nashda.question.dto.response.QuestionFileResDto;
import com.ssafy.nashda.question.dto.response.QuestionResDto;
import com.ssafy.nashda.question.dto.response.ReplyResDto;
import com.ssafy.nashda.question.entity.Reply;
import com.ssafy.nashda.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final MemberController memberController;
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> createQuestion(@RequestHeader("Authorization") String accessToken,
                                                                     @RequestPart("title") String title,
                                                                     @RequestPart("content") String content,
                                                                     @RequestPart(value = "files", required = false)List<MultipartFile> files) {
        Member member = memberController.findMemberByToken(accessToken);

        QuestionReqDto questionReqDto = new QuestionReqDto();
        questionReqDto.setTitle(title);
        questionReqDto.setContent(content);

        questionService.createQuestion(member, questionReqDto, files);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "문의글 생성 성공"));
    }

    @GetMapping
    public ResponseEntity<? extends BaseResponseBody> getQuestions(@RequestHeader("Authorization") String accessToken) {
        Member member = memberController.findMemberByToken(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "문의글 전체 조회 성공", questionService.getQuestions(member)));
    }
    @GetMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> getQuestion(@PathVariable Long index) {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "문의글 상세 조회 성공", questionService.getQuestion(index)));
    }

    @PutMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> updateQuestion(@PathVariable Long index,
                                                                     @RequestHeader("Authorization") String accessToken,
                                                                     @RequestPart("title") String title,
                                                                     @RequestPart("content") String content,
                                                                     @RequestPart(value = "files", required = false)List<MultipartFile> files) {
        Member member = memberController.findMemberByToken(accessToken);

        QuestionReqDto questionReqDto = new QuestionReqDto();
        questionReqDto.setTitle(title);
        questionReqDto.setContent(content);

        questionService.updateQuestion(member, index, questionReqDto, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "문의글 수정 성공"));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> deleteQuestion(@PathVariable Long index,
                                                                     @RequestHeader("Authorization") String accessToken) {
        Member member = memberController.findMemberByToken(accessToken);
        questionService.deleteQuestion(member, index);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(204, "문의글 삭제 성공"));
    }


}
