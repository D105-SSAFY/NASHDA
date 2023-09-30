package com.ssafy.nashda.history.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.history.dto.HistoryResDto;
import com.ssafy.nashda.history.entity.MemberHistory;
import com.ssafy.nashda.history.service.MemberHistoryService;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.dto.response.DomainResDto;
import com.ssafy.nashda.member.entity.Hobby;
import com.ssafy.nashda.member.entity.Job;
import com.ssafy.nashda.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class MemberHistoryController {

    private final MemberController memberController;
    private final MemberHistoryService memberHistoryService;
    @GetMapping("/all")
    public ResponseEntity<? extends BaseResponseBody> getMemberHistory(@RequestHeader("Authorization") String token) {
        Member member = memberController.findMemberByToken(token);

        HistoryResDto historyResDto = memberHistoryService.getMemberHistory(member);
        return ResponseEntity.status(200).body(new BaseResponseBody<>(200, "회원 전체 기록 조회 성공", historyResDto));
    }

}
