package com.ssafy.nashda.notice;


import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.notice.dto.NoticeAllResDto;
import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.service.NoticeService;
import com.ssafy.nashda.token.config.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final MemberController memberController;
    private final NoticeService noticeService;

    private TokenProvider tokenProvider;


    // 공지사항 글 생성
    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> createNotice(@RequestHeader("Authorization") String accessToken,
                                                                   @RequestBody NoticeReqDto noticeReqDto) {

            Member member = memberController.findMemberByToken(accessToken);
            noticeService.createNotice(member, noticeReqDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201,"공지사항 생성 성공"));
    }


    // 공지사항 전체 조회
    @GetMapping
    public ResponseEntity<? extends BaseResponseBody> getNotices() {

        List<NoticeAllResDto> notices = noticeService.getNotices()
                .stream()
                .map(NoticeAllResDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "공지사항 전체 조회 성공", notices));
    }

    @GetMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> getNotice(@PathVariable Long index) {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "공지사항 상세 조회 성공", noticeService.getNotice(index)));
    }

    @PutMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> updateNotice(@PathVariable Long index,
                                                                   @RequestHeader("Authorization") String accessToken,
                                                                   @RequestBody NoticeReqDto noticeReqDto) {

        Member member = memberController.findMemberByToken(accessToken);
        noticeService.updateNotice(member, index, noticeReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "공지사항 수정 성공"));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> deleteNotice(@PathVariable Long index,
                                                                   @RequestHeader("Authorization") String accessToken) {

        Member member = memberController.findMemberByToken(accessToken);
        noticeService.deleteNotice(member, index);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(204, "공지사항 삭제 성공"));
    }

}
