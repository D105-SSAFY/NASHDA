package com.ssafy.nashda.notice;


import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.member.service.MemberServiceImpl;
import com.ssafy.nashda.notice.dto.NoticeAllResDto;
import com.ssafy.nashda.notice.dto.NoticeReqDto;
import com.ssafy.nashda.notice.service.NoticeService;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final MemberService memberService;
    private final TokenService tokenService;
    private final NoticeService noticeService;

    private TokenProvider tokenProvider;


    // 공지사항 글 생성
    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> createNotice(@RequestHeader("Authorization") String bearerToken,
                                                                   @RequestBody NoticeReqDto noticeReqDto) {

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String accessToken = bearerToken.substring("Bearer ".length());
            String email = tokenProvider.getUserEmail(accessToken);
            Member member = memberService.findByEmail(email);
            // member type 확인 하는 작업 추가 필요함.
            noticeService.createNotice(member, noticeReqDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody<>(201,"공지사항 생성 성공"));
        }

        return ResponseEntity.badRequest().body(new BadRequestException(ErrorCode.NOT_VALID_TOKEN));
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
                                                                   @RequestBody NoticeReqDto noticeReqDto) {
        noticeService.updateNotice(index, noticeReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseBody(201, "공지사항 수정 성공"));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<? extends BaseResponseBody> deleteNotice(@PathVariable Long index) {
        noticeService.deleteNotice(index);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(204, "공지사항 삭제 성공"));
    }

}
