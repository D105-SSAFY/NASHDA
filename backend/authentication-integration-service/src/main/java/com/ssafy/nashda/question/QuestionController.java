package com.ssafy.nashda.question;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.QuestionResDto;
import com.ssafy.nashda.question.dto.ReplyResDto;
import com.ssafy.nashda.question.entity.Reply;
import com.ssafy.nashda.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> createQuestion(
                                                                    @RequestBody QuestionReqDto questionReqDto) {
        questionService.createQuestion(questionReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "문의글 생성 성공"));
    }

    @GetMapping
    public ResponseEntity<? extends BaseResponseBody> getQuestions() {
        List<QuestionResDto> questions = questionService.getQuestions()
                .stream()
                .map(question -> {
                    Reply reply = question.getReply();
                    ReplyResDto replyResDto = (reply != null) ? new ReplyResDto(reply) : null;
                    return new QuestionResDto(question, replyResDto);
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "문의글 전체 조회 성공", questions));

    }
    @GetMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> getQuestion(@PathVariable Long index) {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "문의글 상세 조회 성공", questionService.getQuestion(index)));
    }

    @PutMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> updateQuestion(@PathVariable Long index,
                                                                   @RequestBody QuestionReqDto questionReqDto) {
        questionService.updateQuestion(index, questionReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "문의글 수정 성공"));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> deleteQuestion(@PathVariable Long index) {
        questionService.deleteQuestion(index);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(204, "문의글 삭제 성공"));
    }


}
