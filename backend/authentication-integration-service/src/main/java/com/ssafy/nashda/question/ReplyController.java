package com.ssafy.nashda.question;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.ReplyReqDto;
import com.ssafy.nashda.question.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question/{index}/reply")
public class ReplyController {
    private final MemberController memberController;
    private final ReplyService replyService;


    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> createReply(@PathVariable Long index,
                                                                  @RequestHeader("Authorization") String accessToken,
                                                                  @RequestBody ReplyReqDto replyReqDto) {

        Member member = memberController.findMemberByToken(accessToken);
        replyService.createReply(member, index, replyReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "답변글 생성 성공"));
    }

    @PutMapping
    public ResponseEntity<? extends BaseResponseBody> updateReply(@PathVariable Long index,
                                                                  @RequestHeader("Authorization") String accessToken,
                                                                  @RequestBody ReplyReqDto replyReqDto) {

        Member member = memberController.findMemberByToken(accessToken);
        replyService.updateReply(member, index, replyReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "답변글 수정 성공"));
    }

    @DeleteMapping
    public ResponseEntity<? extends BaseResponseBody> deleteReply(@PathVariable Long index,
                                                                  @RequestHeader("Authorization") String accessToken) {

        Member member = memberController.findMemberByToken(accessToken);
        replyService.deleteReply(member, index);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(204, "답변 글 삭제 성공"));
    }
}
