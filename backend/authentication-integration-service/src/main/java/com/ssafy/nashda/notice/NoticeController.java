package com.ssafy.nashda.notice;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.dto.NoticeResDto;
import com.ssafy.nashda.notice.entity.Notice;
import com.ssafy.nashda.notice.service.NoticeService;
import com.ssafy.nashda.user.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
//    private final MemberController memberController;
    private final NoticeService noticeService;

    // 공지사항 글 생성
    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> insert(
                                                            @RequestBody NoticeReqDto noticeReqDto) {
//        Member member = memberController.findMemberByToken(accessToken);

        noticeService.insert(noticeReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201,"공지사항 생성 성공"));
    }

    // 공지사항 전체 조회
    @GetMapping
    public ResponseEntity<? extends BaseResponseBody> findAll() {
        List<NoticeResDto> notices = noticeService.findAll()
                .stream()
                .map(NoticeResDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "공지사항 전체 조회 성공", notices));
    }

}
