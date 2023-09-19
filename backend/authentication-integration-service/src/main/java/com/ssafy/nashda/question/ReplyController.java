package com.ssafy.nashda.question;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.question.dto.QuestionReqDto;
import com.ssafy.nashda.question.dto.ReplyReqDto;
import com.ssafy.nashda.question.repository.ReplyRepository;
import com.ssafy.nashda.question.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question/{index}/reply")
public class ReplyController {
    private final ReplyService replyService;


    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> createReply(@PathVariable Long index,
                                                                  @RequestBody ReplyReqDto replyReqDto) {
        replyService.createReply(index, replyReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "답변글 생성 성공"));
    }

    @PutMapping
    public ResponseEntity<? extends BaseResponseBody> updateReply(@PathVariable Long index,
                                                                  @RequestBody ReplyReqDto replyReqDto) {
        replyService.updateReply(index, replyReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "답변글 수정 성공"));
    }

    @DeleteMapping
    public ResponseEntity<? extends BaseResponseBody> deleteReply(@PathVariable Long index) {
        replyService.deleteReply(index);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(204, "답변 글 삭제 성공"));
    }
}
